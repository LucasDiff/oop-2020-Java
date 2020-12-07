package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool<T extends Actor> extends AbstractActor implements Usable<T> {

    private int remainingUses;

    public BreakableTool(int uses)
    {
        setRemainingUses(uses);
    }

    @Override
    public void useWith(T actor)
    {
        if (getRemainingUses() <= 0) {
            if(getScene()==null)return;
            getScene().removeActor(this);
            return;
        }

        setRemainingUses(getRemainingUses() - 1);
    }

    public int getRemainingUses()
    {
        return remainingUses;
    }

    public void setRemainingUses(int remainingUses)
    {
        this.remainingUses = remainingUses;

        if (this.remainingUses <= 0) {
            if(getScene()==null)return;
            getScene().removeActor(this);
        }
    }


}
