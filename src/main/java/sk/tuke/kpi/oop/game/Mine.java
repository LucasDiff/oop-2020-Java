package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.openables.Door;

import java.util.Optional;

public class Mine extends AbstractActor {

    public Mine() {
        setAnimation(new Animation("sprites/mine.png", 16, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
    }


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.deadly(scene);
        scene.getMessageBus().subscribeOnce(Door.DOOR_OPENED, a -> {
            setAnimation(new Animation("sprites/barrel.png", 16, 16));
        });
    }

    private void deadly(@NotNull Scene scene) {
        new When<>(
            action -> this.intersectsPlayer(scene).isPresent(),
            new Invoke<>(() -> {
                Optional<?> actor = this.intersectsPlayer(scene);
                if (!actor.isPresent()) {
                    return;
                }
                setAnimation(new Animation("sprites/small_explosion.png", 16, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG));

                ((Alive) actor.get()).getHealth().drain(1000);
                scene.getMessageBus().subscribeOnce(Ripley.RIPLEY_DIED, a -> {

                });
                // this.deadly(scene);
            })
        ).scheduleFor(this);
    }

    @NotNull
    private Optional<?> intersectsPlayer(@NotNull Scene scene) {
        return scene.getActors().stream()
            .filter(Alive.class::isInstance)
            .filter(this::intersects)
            .filter(actor -> !(actor instanceof Enemy))
            .filter(actor -> !actor.equals(this))
            .findFirst();
    }

}
