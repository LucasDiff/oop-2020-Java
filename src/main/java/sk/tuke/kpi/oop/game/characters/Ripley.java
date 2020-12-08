package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

public class Ripley extends AbstractActor implements Armed, Movable, Alive, Keeper<Collectible> {

    private Animation normal;

    private Backpack<Collectible> backpack;

    private Health health;

    private Firearm firearm;

    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);
    private Animation getNormal()
    {
        return normal;
    }

    private void setNormal(Animation normal)
    {
        this.normal = normal;
    }

    @Override
    public Backpack<Collectible> getBackpack()
    {
        return backpack;
    }

    @Override
    public Health getHealth()
    {
        return health;
    }

    @Override
    public Firearm getFirearm()
    {
        return firearm;
    }

    @Override
    public void setFirearm(Firearm weapon)
    {
        this.firearm = weapon;
    }

    @Override
    public int getSpeed()
    {
        return 2;
    }

    @Override
    public void startedMoving(Direction direction)
    {
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }
    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        Overlay overlay = scene.getGame().getOverlay();

        new Loop<>(
            new Invoke<>(() -> {
                overlay.drawText(" | HP: " + getHealth().getValue(), 100, scene.getGame().getWindowSetup().getHeight() - GameApplication.STATUS_LINE_OFFSET);
    //            overlay.drawText(" | AMMO: " + getFirearm().getAmmo(), 250, scene.getGame().getWindowSetup().getHeight() - GameApplication.STATUS_LINE_OFFSET);
            })
        ).scheduleOn(scene);
    }
    @Override
    public void stoppedMoving()
    {
        getAnimation().stop();
    }

    public Ripley() {
        super("Ellen");

        this.setNormal(new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.getNormal().stop();

        health = new Health(1000);
        backpack = new Backpack<Collectible>("Ripley's backpack", 5);
        firearm = new Gun(0, 500);

        health.onExhaustion(() -> {
            if (getScene() != null) {
                getScene().getMessageBus().publish(RIPLEY_DIED, this);
            }
        });

        setAnimation(this.getNormal());
    }
}
