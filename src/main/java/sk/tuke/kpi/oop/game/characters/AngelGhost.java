
package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.graphics.Animation;


import sk.tuke.kpi.oop.game.behaviours.Behaviour;


import java.util.Optional;


public class AngelGhost extends Alien {

    public AngelGhost()
    {
        this(100, null);
    }

    public AngelGhost(Behaviour<? super Alien> behaviour)
    {
        this(100, behaviour);
    }
    @NotNull
    private Optional<?> intersectsPlayer(@NotNull Scene scene)
    {
        return scene.getActors().stream()
            .filter(Alive.class::isInstance)
            .filter(this::intersects)

            .filter(actor -> !(actor instanceof Enemy))

            .filter(actor -> !actor.equals(this))

            .findFirst();
    }
    private void deadly(@NotNull Scene scene)
    {
        new When<>(
            action -> this.intersectsPlayer(scene).isPresent(),
            new Invoke<>(() -> {
                Optional<?> actor = this.intersectsPlayer(scene);
                if (!actor.isPresent()) {
                    return;
                }

                ((Alive) actor.get()).getHealth().restore();
                this.deadly(scene);
            })
        ).scheduleFor(this);
    }


    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        this.deadly(scene);


    }

    public AngelGhost(int healthValue, Behaviour<? super Alien> behaviour)
    {
        super(healthValue, behaviour);

        setAnimation(new Animation("sprites/ghost.png", 64, 64, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();

        this.setHealth(new Health(200));
        this.getHealth().onExhaustion(this::die);
    }
}
