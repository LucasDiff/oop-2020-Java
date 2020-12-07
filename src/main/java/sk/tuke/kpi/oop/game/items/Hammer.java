
package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Repairable;

public class Hammer extends BreakableTool<Repairable> implements Collectible {

    public Hammer()
    {
        super(1);
        setAnimation(new Animation("sprites/hammer.png", 16, 16));
    }

    @Override
    public void useWith(Repairable actor)
    {
        if (actor == null) {
            return;
        }

        if (actor.repair()) {
            setRemainingUses(getRemainingUses() - 1);
            if (getRemainingUses() <= 0) {
                getScene().removeActor(this);
            }
        }
    }

    @Override
    public Class<Repairable> getUsingActorClass()
    {
        return Repairable.class;
    }
}
