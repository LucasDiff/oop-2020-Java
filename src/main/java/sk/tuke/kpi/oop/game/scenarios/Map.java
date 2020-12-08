package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.oop.game.*;
import sk.tuke.kpi.oop.game.Bbuilder.Builder;
import sk.tuke.kpi.oop.game.Bbuilder.Director;
import sk.tuke.kpi.oop.game.Bbuilder.RipleyBuilder;
import sk.tuke.kpi.oop.game.Bbuilder.RipleyDirector;

import sk.tuke.kpi.oop.game.Pprototype.ProductType;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AngelGhost;
import sk.tuke.kpi.oop.game.characters.Monster;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.controllers.SkuskaController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;
import sk.tuke.kpi.oop.game.prototypeJava2.MyFactory;
import sk.tuke.kpi.oop.game.prototypeJava2.MyProductType;

import java.util.List;

public class Map implements SceneListener {
    private int aliens;


    public static class Factory extends AbstractActor implements ActorFactory, Counter {

        @Nullable
        // @Override

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

                case "item":
                    switch (type) {
                        case "energy":
                            return new Energy();
                        case "ammo":
                            return new Ammo();
                        case "card":
                            return new AccessCard();
                        case "hammer":
                            return new Hammer();
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

                case "locker":
                    switch (type) {
                        default:
                            return null;
                    }

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

    int spocitaj(Scene scene) {
        int counter = 0;
        if (scene == null) return 0;

        boolean i = true;

        for (Actor alien : scene.getActors()) {
            if (alien instanceof Alien) {
                counter++;
            }
        }
        return counter;
    }


    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        MyFactory myFactory = new MyFactory();
        myFactory.createProduct(MyProductType.KeyY);


        Ripley ripley = scene.getFirstActorByType(Ripley.class);

        //for(Hammer hammer:ripley.getContainer())

        scene.setActorRenderOrder(List.of(Ripley.class));

        Overlay overlay = scene.getGame().getOverlay();


        scene.getGame().pushActorContainer(ripley.getContainer());
        scene.follow(ripley);
        scene.getInput().registerListener(new SkuskaController(scene));


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
        //Teleport teleport=scene.getFirstActorByType(Teleport.class);
        Director Riplay = new RipleyDirector();

        Builder riplay = new RipleyBuilder();
        sk.tuke.kpi.oop.game.Pprototype.Factory factory = new sk.tuke.kpi.oop.game.Pprototype.Factory();


        scene.getMessageBus().subscribeOnce(Ripley.RIPLEY_DIED, a -> {

            RipleyState.getInstance().saveFile("RIPLEY_DIED", factory.createProduct(ProductType.Defeat).toString());
            new CommandEndGame(RipleyState.getInstance().loadFile("RIPLEY_DIED")).execute(a);

            disposableMovable.dispose();
            disposableKeeper.dispose();

            riplay.stopMove();
            disposableShooter.dispose();
        });
        riplay.stopMove();

        scene.getMessageBus().subscribeOnce(Button.BUTTON_WIN, a -> {


            RipleyState.getInstance().saveFile("BUTTON_WIN", factory.createProduct(ProductType.Victory).toString());
            new CommandEndGame(
                RipleyState.getInstance().loadFile("BUTTON_WIN")
            ).execute(a);


            disposableMovable.dispose();
            disposableKeeper.dispose();
            disposableShooter.dispose();
        });


    }


    @Override
    public void sceneCreated(@NotNull Scene scene) {

        scene.getMessageBus().subscribe(Alien.ALIEN_BORN, action -> aliens++);

        scene.getMessageBus().subscribe(Alien.ALIEN_DIED, action -> aliens--);
    }
}
