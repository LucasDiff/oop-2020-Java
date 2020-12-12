package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;

import java.util.Optional;

public class Lift extends AbstractActor implements Switchable {
    public static final Topic<Lift> LIFT_ACTIVATED = Topic.create("lift activated", Lift.class);
    private boolean isActive;
    private int counter = 0;

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
        super.addedToScene(scene);
        Actor liftSwitchActor = scene.getFirstActorByName("LiftSwitch");
        if (liftSwitchActor instanceof LiftSwitch) {
            ((LiftSwitch) liftSwitchActor).setLift(this);
        }
        checkWin(scene);
    }

    private void checkWin(@NotNull Scene scene) {
        new When<>(
            action -> this.intersectsPlayer(scene).isPresent(),
            new Invoke<>(() -> {
                getScene().getMessageBus().publish(LIFT_ACTIVATED, this);
            })
        ).scheduleFor(this);
    }

    @NotNull
    private Optional<?> intersectsPlayer(@NotNull Scene scene) {
        if (!isOn()) {
            return Optional.empty();
        }
        return scene.getActors().stream()
            .filter(Alive.class::isInstance)
            .filter(this::intersects)
            .filter(actor -> !(actor instanceof Enemy))
            .filter(actor -> !actor.equals(this))
            .findFirst();
    }
}

