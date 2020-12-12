package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class CuttingTool<T extends Actor> extends AbstractActor implements Usable<T> {

    private int sharpnessLevel;

    public CuttingTool(int sharpnessLevel) {
        setSharpnessLevel(sharpnessLevel);
    }

    @Override
    public void useWith(T actor) {
        if (getSharpnessLevel() <= 0) {
            if (getScene() == null) return;
            actor.
            getScene().removeActor(this);
            return;
        }

        setSharpnessLevel(getSharpnessLevel() - 1);
    }

    public int getSharpnessLevel() {
        return sharpnessLevel;
    }

    public void setSharpnessLevel(int sharpnessLevel) {
        this.sharpnessLevel = sharpnessLevel;

        if (this.sharpnessLevel <= 0) {
            if (getScene() == null) return;
            getScene().removeActor(this);
        }
    }
}
