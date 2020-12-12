package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Lift extends AbstractActor implements Switchable {

    private boolean isActive;

    public Lift() {
        super("Lift");
        turnOff();
    }

    @Override
    public void turnOff() {
        isActive = false;
        setAnimation(new Animation("sprites/lift_inactive.png", 48, 48, 0.2f, Animation.PlayMode.ONCE));
    }

    @Override
    public boolean isOn() {
        return isActive;
    }

    @Override
    public void turnOn() {
        isActive = true;
        setAnimation(new Animation("sprites/lift_active.png", 48, 48, 0.2f, Animation.PlayMode.ONCE));
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        Actor liftSwitchActor = scene.getFirstActorByName("LiftSwitch");
        if (liftSwitchActor instanceof LiftSwitch) {
            ((LiftSwitch) liftSwitchActor).setLift(this);
        }
    }
}

