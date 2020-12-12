

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class LargeExplosion extends AbstractActor {
    private Animation animation;

    public LargeExplosion() {
        this.animation = new Animation("sprites/large_explosion.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
        animation.stop();
    }

    public void start() {
        setAnimation(animation);
        animation.play();
        new When<>(
            (action) -> this.getAnimation().getCurrentFrameIndex() >= (this.getAnimation().getFrameCount() - 1),
            new Invoke<>(() -> {
                Scene scene = this.getScene();
                if (scene != null) {
                    scene.removeActor(this);
                }
            })
        ).scheduleFor(this);
    }
}
