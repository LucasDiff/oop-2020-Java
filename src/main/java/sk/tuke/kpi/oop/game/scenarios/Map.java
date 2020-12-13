package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.*;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.*;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.FinalDoor;
import sk.tuke.kpi.oop.game.openables.LockedDoor;
import sk.tuke.kpi.oop.game.prototype.ProductType;
import sk.tuke.kpi.oop.game.prototypeJava2.MyFactory;
import sk.tuke.kpi.oop.game.prototypeJava2.MyProductType;
import sk.tuke.kpi.oop.game.weapons.FuturisticGun;

import java.util.List;

public class Map implements SceneListener {
    private int aliens;


    public static class Factory extends AbstractActor implements ActorFactory, Counter {

        public int counter;

        public Actor create(@Nullable String type, @Nullable String name) {
            if (type == null) {
                return null;
            }

            if (name == null) {
                return null;
            }

            switch (name) {
                case "alien":
                    return new Alien(50, new RandomlyMoving());

                case "player":
                    return new Ripley();

                case "monster":
                    return new Monster(50, new RandomlyMoving());

                case "mine":
                    return new Mine();

                case "door":
                    if (type.equalsIgnoreCase("horizontal")) {
                        return new Door(name, Door.Orientation.HORIZONTAL);
                    } else {
                        return new Door(name, Door.Orientation.VERTICAL);
                    }
                case "LockedDoor":
                    if (type.equalsIgnoreCase("horizontal")) {
                        return new LockedDoor(name, Door.Orientation.HORIZONTAL);
                    } else {
                        return new LockedDoor(name, Door.Orientation.VERTICAL);
                    }
                case "FinalDoor":
                    if (type.equalsIgnoreCase("horizontal")) {
                        return new FinalDoor(name, Door.Orientation.HORIZONTAL);
                    } else {
                        return new FinalDoor(name, Door.Orientation.VERTICAL);
                    }

                case "item":
                    switch (type) {
                        case "energy":
                            return new Energy();
                        case "ammo":
                            return new Ammo();
                        case "card":
                            return new AccessCard();
                        case "BlueCard":
                            return new FinalAccessCard();
                        case "hammer":
                            return new Hammer();
                        case "scissors":
                            return new Scissors();
                        default:
                            return null;
                    }

                case "card":
                    return new Box(new AccessCard());
                case "hammer":
                    return new Box(new Hammer());
                case "ventilator":
                    return new Ventilator();
                case "ammo":
                    return new Ammo();
                case "teleport":
                    return new Teleport();
                case "angelghost":
                    return new AngelGhost(50, new RandomlyMoving());
                case "button":
                    return new Button();
                case "generator":
                    return new Win();
                case "lift":
                    return new Lift();
                case "LiftSwitch":
                    return new LiftSwitch();
                case "GiftPackage":
                    FuturisticGun gun = new FuturisticGun();
                    GiftPackage giftPackage = new GiftPackage();
                    giftPackage.setGift(gun);
                    return giftPackage;

                default:
                    return null;
            }
        }

        @Override
        public int getCounter() {
            return this.counter;
        }

        @Override
        public int setCounter() {
            return this.counter + 1;
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        MyFactory myFactory = new MyFactory();
        myFactory.createProduct(MyProductType.KeyY);


        Ripley ripley = scene.getFirstActorByType(Ripley.class);

        scene.setActorRenderOrder(List.of(Ripley.class));

        scene.getGame().pushActorContainer(ripley.getBackpack());
        scene.follow(ripley);

        new Loop<>(
            new Invoke<>(() -> {
                scene.getGame().getOverlay()
                    .drawText("ALIENS: " + aliens, 15,
                        scene.getGame().getWindowSetup().getHeight() - (GameApplication.STATUS_LINE_OFFSET * 2));
            })
        ).scheduleOn(scene);


        Disposable disposableMovable = scene.getInput().registerListener(new MovableController(ripley));
        Disposable disposableKeeper = scene.getInput().registerListener(new KeeperController(ripley));
        Disposable disposableShooter = scene.getInput().registerListener(new ShooterController(ripley));

        sk.tuke.kpi.oop.game.prototype.Factory factory = new sk.tuke.kpi.oop.game.prototype.Factory();

        scene.getMessageBus().subscribeOnce(Ripley.RIPLEY_DIED, a -> {
            RipleyState.getInstance().saveFile("RIPLEY_DIED", factory.createProduct(ProductType.Defeat).toString());
            new CommandEndGame(RipleyState.getInstance().loadFile("RIPLEY_DIED"), false).execute(a);
            disposableMovable.dispose();
            disposableKeeper.dispose();
            disposableShooter.dispose();
        });

        scene.getMessageBus().subscribeOnce(Ripley.RIPLEY_WON, a -> {
            RipleyState.getInstance().saveFile("RIPLEY_WON", factory.createProduct(ProductType.Victory).toString());
            new CommandEndGame(RipleyState.getInstance().loadFile("RIPLEY_WON"), true).execute(a);
            disposableMovable.dispose();
            disposableKeeper.dispose();
            disposableShooter.dispose();
        });
    }


    @Override
    public void sceneCreated(@NotNull Scene scene) {
        scene.getMessageBus().subscribe(Alien.ALIEN_BORN, action -> aliens++);
        scene.getMessageBus().subscribe(Alien.ALIEN_DIED, action -> aliens--);
        scene.getMessageBus().subscribe(Alien.ALIEN_DIED, alien -> smallBang(scene, alien));
        scene.getMessageBus().subscribe(MotherAlien.MOTHER_ALIEN_DIED, motherAlien -> largeBang(scene, motherAlien));
    }

    private void smallBang(Scene scene, Alien alien) {
        SmallExplosion explosion = new SmallExplosion();
        scene.addActor(explosion, alien.getPosX() + 8, alien.getPosY() + 8);
        explosion.start();
    }

    private void largeBang(Scene scene, MotherAlien motherAlien) {
        for (int i = 0; i < 4; i++) {
            LargeExplosion explosion = new LargeExplosion();
            scene.addActor(explosion, motherAlien.getPosX() + 32, motherAlien.getPosY() + 32 * (i + 1));
            explosion.start();
        }
    }
}
