package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.FinalDoor;


public class FinalAccessCard extends AbstractActor implements Collectible, Usable<FinalDoor> {


    public FinalAccessCard() {
        setAnimation(new Animation("sprites/key_blue.png", 16, 16));
    }

    @Override
    public void useWith(FinalDoor actor) {
        actor.open();
    }

    @Override
    public Class<FinalDoor> getUsingActorClass() {
        return FinalDoor.class;
    }

    @Override
    public boolean oneTimeUsable() {
        return true;
    }
}
