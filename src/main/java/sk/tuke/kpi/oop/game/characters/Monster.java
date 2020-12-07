
package sk.tuke.kpi.oop.game.characters;


import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import sk.tuke.kpi.oop.game.openables.Door;


import java.util.Optional;


public class Monster extends AbstractActor implements Alive, Enemy, Movable {

    private Health health;

    private Behaviour<? super Alien> behaviour;




    public Monster(int healthValue, Behaviour<? super Alien> behaviour)
    {
    super("monster");

        setAnimation(new Animation("sprites/monster.png", 72, 128, 0.1f, Animation.PlayMode.LOOP_PINGPONG));


    }


    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);
        setAnimation(new Animation("sprites/monster.png", 72, 128, 0.1f, Animation.PlayMode.LOOP_PINGPONG));

        //this.setAnimation(new Animation("sprites/monster.png", 216, 128,0.1f, Animation.PlayMode.LOOP_PINGPONG));

        this.deadly(scene);
        scene.getMessageBus().subscribeOnce(Door.Door_open, a -> {
            setAnimation(new Animation("sprites/barrel.png",16,16));
        });
        //if (this.getBehaviour() != null) {
           // this.getBehaviour().setUp(this);
        //}
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

                ((Alive) actor.get()).getHealth().drain(10000);
                this.deadly(scene);
            })
        ).scheduleFor(this);
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


    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Health getHealth() {
        return null;
    }
}
