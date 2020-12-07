

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

/**
 * The type Time bomb.
 */
public class TimeBomb extends AbstractActor {

    private Animation inactiveAnimation;
    private Animation activeAnimation;
    private Animation explosionAnimation;

    private float time;

    private boolean activated;

    /**
     * Instantiates a new Time bomb.
     *
     * @param time the time
     */
    public TimeBomb(float time)
    {
        this.setInactiveAnimation(new Animation("sprites/bomb.png", 16, 16));
        this.setActiveAnimation(new Animation("sprites/bomb_activated.png", 16, 16, 0.02f, Animation.PlayMode.LOOP_PINGPONG));
        this.setExplosionAnimation(new Animation("sprites/small_explosion.png", 16, 16, 0.1f, Animation.PlayMode.ONCE));

        this.setTime(time);

        setAnimation(this.getInactiveAnimation());
    }

    /**
     * Activate.
     */
    public void activate()
    {
        if (this.isActivated()) {
            return;
        }

        setAnimation(this.getActiveAnimation());
        this.activated = true;

        new ActionSequence<>(
            new Wait<>(this.getTime()),
            new Invoke<>(this::detonate)
        ).scheduleFor(this);
    }

    /**
     * Detonate.
     */
    public void detonate()
    {
        setAnimation(this.getExplosionAnimation());

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

    @Contract(pure = true)
    private float getTime()
    {
        return time;
    }

    private void setTime(float time)
    {
        this.time = time;
    }

    /**
     * Is activated boolean.
     *
     * @return the boolean
     */
    public boolean isActivated()
    {
        return activated;
    }

    @Contract(pure = true)
    private Animation getInactiveAnimation()
    {
        return inactiveAnimation;
    }

    private void setInactiveAnimation(Animation inactiveAnimation)
    {
        this.inactiveAnimation = inactiveAnimation;
    }

    @Contract(pure = true)
    private Animation getActiveAnimation()
    {
        return activeAnimation;
    }

    private void setActiveAnimation(Animation activeAnimation)
    {
        this.activeAnimation = activeAnimation;
    }

    @Contract(pure = true)
    private Animation getExplosionAnimation()
    {
        return explosionAnimation;
    }

    private void setExplosionAnimation(Animation explosionAnimation)
    {
        this.explosionAnimation = explosionAnimation;
    }
}
