package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.oop.game.DefectiveLight;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Wrench extends BreakableTool<DefectiveLight> implements Collectible {

    public Wrench()
    {
        super(2);
        setAnimation(new Animation("sprites/wrench.png", 16, 16));
    }

    @Override
    public void useWith(DefectiveLight actor)
    {
        if (getRemainingUses() <= 0) {
            return;
        }

        if (actor == null || !actor.repair()) {
            return;
        }

        setRemainingUses(getRemainingUses() - 1);
    }

    @Override
    public Class<DefectiveLight> getUsingActorClass() {
        return DefectiveLight.class;
    }


}
