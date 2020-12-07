
package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

/**
 * The type Light.
 */
public class Light extends AbstractActor implements Switchable, EnergyConsumer {

    private Animation animationOn;
    private Animation animationOff;

    private boolean on;
    private boolean powered;

    /**
     * Instantiates a new Light.
     */
    public Light()
    {
        this.setOn(false);
        this.setPowered(false);

        this.setAnimationOn(new Animation("sprites/light_on.png", 16, 16));
        this.setAnimationOff(new Animation("sprites/light_off.png", 16, 16));
        this.updateAnimation();
    }

    private void updateAnimation()
    {
        if (this.isOn() && this.isPowered()) {
            setAnimation(this.getAnimationOn());
            return;
        }

        setAnimation(this.getAnimationOff());
    }

    @Contract(pure = true)
    private boolean isPowered()
    {
        return powered;
    }

    @Override
    public void setPowered(boolean powered)
    {
        this.powered = powered;
        this.updateAnimation();
    }

    public void turnOn()
    {
        this.setOn(true);
    }

    public void turnOff()
    {
        this.setOn(false);
    }

    /**
     * Toggle.
     */
    public void toggle()
    {
        if (this.isOn()) {
            this.setOn(false);
        } else {
            this.setOn(true);
        }
    }

    @Override
    public boolean isOn()
    {
        return on;
    }

    private void setOn(boolean on)
    {
        this.on = on;
        this.updateAnimation();
    }

    @Contract(pure = true)
    private Animation getAnimationOn()
    {
        return animationOn;
    }

    private void setAnimationOn(Animation animationOn)
    {
        this.animationOn = animationOn;
    }

    @Contract(pure = true)
    private Animation getAnimationOff()
    {
        return animationOff;
    }

    private void setAnimationOff(Animation animationOff)
    {
        this.animationOff = animationOff;
    }
}
