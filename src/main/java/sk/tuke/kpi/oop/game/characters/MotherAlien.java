

package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.openables.Door;

import java.util.Optional;

public class MotherAlien extends AbstractActor implements Alive, Enemy, Movable {
    private Health health;
    private Behaviour<? super Alien> behaviour;


    public MotherAlien(int healthValue, Behaviour<? super Alien> behaviour) {
        super("mother");
        this.health = new Health(healthValue);
        this.behaviour = behaviour;
        this.getHealth().onExhaustion(this::die);
        setAnimation(new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        setAnimation(new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG));

        deadly(scene);
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

                ((Alive) actor.get()).getHealth().drain(10000);
                this.deadly(scene);
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

    private void die() {

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Health getHealth() {
        return health;
    }
}
