package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {

    private Reactor reactor;
    private boolean isWorking;

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

    public void coolReactor() {
        if (reactor != null && isWorking) {
            reactor.decreaseTemperature(1);
        }
    }
    @Override
    public void turnOn() {
        isWorking = true;
    }

    @Override
    public boolean isOn() {
        return isWorking;
    }

    @Override
    public void turnOff() {
        isWorking = false;
    }
}
