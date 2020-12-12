

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FinalAnimation extends AbstractActor {
    private Animation animation;

    public FinalAnimation() {
        this.animation = new Animation("sprites/lift_anim.png", 48, 48, 0.1f, Animation.PlayMode.ONCE);
        animation.stop();
    }

    public void start() {
        setAnimation(animation);
        animation.play();
    }
}
