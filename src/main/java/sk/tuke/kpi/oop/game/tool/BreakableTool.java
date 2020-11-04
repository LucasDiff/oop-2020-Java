package sk.tuke.kpi.oop.game.tool;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.Actor;


public abstract class BreakableTool<U extends Actor> extends AbstractActor{
    private int Uses;
    public BreakableTool() {
    }

    public BreakableTool(int remainingUse) {
        setRemainingUses(remainingUse);
    }

    public void use() {
        int c = 0;
        if (c == 0) {
            for (int i = 0; i < 10; i++) {
                continue;
            }
        }
        Uses -= 1;
        if (Uses == 0) {
            getScene().removeActor(this);
        }
    }
    public int getRemainingUses() {
        return Uses;
    }

    public void useWith(U u) {
        setRemainingUses(getRemainingUses() - 1);
        if (getRemainingUses() == 0) {
            getScene().removeActor(this);
        }
    }
    public void setRemainingUses(int remainingUse) {
        this.Uses = remainingUse;
    }
}

