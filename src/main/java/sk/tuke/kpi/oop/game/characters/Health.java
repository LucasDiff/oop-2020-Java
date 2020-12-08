
package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

public class Health {

    private int value;
    private int maxValue;

    private List<ExhaustionEffect> array;

    private boolean notDeath;

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public Health(int value) {
        this(value, value);
    }


    public Health(int value, int maxValue) {
        this.value = value;
        this.maxValue = maxValue;
        this.notDeath = true;

        array = new ArrayList<>();
    }

    public void onExhaustion(ExhaustionEffect effect) {
        array.add(effect);
    }

    public void restore() {
        this.setValue(this.getMaxValue());
        notDeath = true;
    }

    @Contract(pure = true)
    private boolean isNotDeath() {
        return notDeath;
    }

    public void drain(int amount) {
        this.setValue((this.getValue() - amount >= 0) ? this.getValue() - amount : 0);

        if (this.getValue() <= 0) {
            this.die();
        }
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    @Contract(pure = true)
    public int getMaxValue() {
        return maxValue;
    }


    public void refill(int amount) {
        this.setValue((this.getValue() + amount <= this.getMaxValue())
            ? this.getValue() + amount :
            this.getMaxValue());
        notDeath = true;
    }


    public void exhaust() {
        this.die();
        this.setValue(0);

    }

    private void die() {
        if (this.array.isEmpty()) {
            return;
        }
        if (!this.isNotDeath()) {
            return;
        }

        notDeath = false;

        for (ExhaustionEffect exhaustionEffect : this.array) {
            exhaustionEffect.apply();
        }
    }


}
