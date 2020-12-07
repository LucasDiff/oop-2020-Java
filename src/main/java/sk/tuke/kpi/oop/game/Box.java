package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Usable;

public class Box extends AbstractActor implements Usable<Actor> {

    private Actor item;

    private boolean empty;
    @Override
    public Class<Actor> getUsingActorClass()
    {
        return Actor.class;
    }
    public Box(Actor item)
    { this.item = item;
    setAnimation(new Animation("sprites/box_large.png", 16, 16));
        this.empty = false;

    }
    @Override
    public void useWith(Actor actor)
    { if (empty) {
        return;
    }
        if (actor == null) {
            return;
        }
        empty = true;

        new CommandAddToScene(getScene(), actor.getPosX(), actor.getPosY()).execute(item);


    }


}
