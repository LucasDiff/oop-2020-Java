

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.util.Random;

/**
 * The type Defective light.
 */
public class DefectiveLight extends Light implements Repairable {

    private Disposable instance;

    private boolean broken;

    /**
     * Instantiates a new Defective light.
     */
    public DefectiveLight() {
        super();
        this.setBroken(true);
    }

    private void defect() {
        this.setBroken(true);
        this.instance = new Loop<>(new Invoke<>(this::defective)).scheduleFor(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.defect();
    }

    private void defective() {
        Random random = new Random();

        if ((random.nextInt((20 - 1) + 1) + 1) == 1) {
            this.toggle();
        }
    }

    @Override
    public boolean repair() {
        if (this.instance == null) {
            return false;
        }

        if (!this.isBroken()) {
            return false;
        }

        this.instance.dispose();
        this.turnOn();
        this.setBroken(false);

//        new ActionSequence<Actor>(new Wait(10), new Invoke<>(this::defect)).scheduleOn(this);

        return true;
    }

    @Contract(pure = true)
    private boolean isBroken() {
        return broken;
    }

    private void setBroken(boolean broken) {
        this.broken = broken;
    }
}
