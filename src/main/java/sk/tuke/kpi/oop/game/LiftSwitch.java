package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Usable;

public class LiftSwitch extends AbstractActor implements Switchable, Usable<Ripley> {
    private boolean isEnabled;
    private Lift lift;

    public LiftSwitch() {
        super("LiftSwitch");
        turnOff();
    }

    @Override
    public void turnOff() {
        isEnabled = false;
        setAnimation(new Animation("sprites/switch_red.png", 16, 16, 0.2f, Animation.PlayMode.ONCE));
        if (lift != null) {
            lift.turnOff();
        }
    }

    @Override
    public boolean isOn() {
        return isEnabled;
    }

    @Override
    public void turnOn() {
        isEnabled = true;
        setAnimation(new Animation("sprites/switch_green.png", 16, 16, 0.2f, Animation.PlayMode.ONCE));
        if (lift != null) {
            lift.turnOn();
        }
    }

    public void toggle() {
        if (isOn()) {
            turnOff();
        } else {
            turnOn();
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        Actor liftActor = scene.getFirstActorByName("Lift");
        if (liftActor instanceof Lift) {
            this.lift = (Lift)liftActor;
        }
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    @Override
    public void useWith(Ripley actor) {
        toggle();
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}

