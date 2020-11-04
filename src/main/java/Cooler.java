import sk.tuke.kpi.gamelib.framework.AbstractActor;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor {

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
}
