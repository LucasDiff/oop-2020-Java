package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {

    private boolean isWorking;
    private Reactor reactor;

    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        isWorking = false;

        setAnimation(new Animation(
            "sprites/fan.png",
            32,
            32,
            0.2f,
            Animation.PlayMode.LOOP_PINGPONG
        ));
    }

    public Reactor getReactor() {
        return reactor;
    }

    @Override
    public void turnOff() {
        isWorking = false;
    }

    @Override
    public boolean isOn() {
        return isWorking;
    }

    @Override
    public void turnOn() {
        isWorking = true;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
    }

    public void coolReactor() {
        if (reactor != null && isWorking) {
            reactor.decreaseTemperature(1);
        }
    }
}

