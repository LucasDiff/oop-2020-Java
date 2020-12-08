

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

/**
 * The type Helicopter.
 */
public class Helicopter extends AbstractActor {

    private Animation normalAnimation;

    private boolean deadly;

    private int speed;
    private int damage;

    /**
     * Instantiates a new Helicopter.
     */
    public Helicopter()
    {
        this.setNormalAnimation(new Animation("sprites/heli.png", 64, 64, 0.08f, Animation.PlayMode.LOOP));
        this.setAnimation(this.getNormalAnimation());

        this.setDeadly(false);
        this.setSpeed(1);
        this.setDamage(1);
    }

    /**
     * Search and destroy.
     */
    public void searchAndDestroy()
    {
        this.setDeadly(true);
        this.follow();
    }

    private void follow()
    {
        if (!this.isDeadly()) {
            return;
        }

        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }

        Player player = scene.getFirstActorByType(Player.class);
        if (player == null) {
            return;
        }

        double diffX = player.getPosX() - this.getPosX();
        double diffY = player.getPosY() - this.getPosY();

        float angle = (float) Math.atan2(diffY, diffX);

        this.getAnimation().setRotation((float) Math.toDegrees(angle) - 90);

        if (this.intersects(player)) {
            player.setEnergy(player.getEnergy() - this.getDamage());
        }

        if ((diffX >= -1 && diffX <= 1) && (diffY >= -1 && diffY <= 1)) {
            new Invoke<>(this::searchAndDestroy).scheduleFor(this);
            return;
        }

        double hyp = Math.sqrt((diffX * diffX) + (diffY * diffY));
        diffX /= hyp;
        diffY /= hyp;

        double posX = (double) this.getPosX();
        double posY = (double) this.getPosY();

        posX += (diffX * this.getSpeed());
        posY += (diffY * this.getSpeed());

        this.setPosition((int) Math.round(posX), (int) Math.round(posY));

        new Invoke<>(this::searchAndDestroy).scheduleFor(this);
    }

    /**
     * Is deadly boolean.
     *
     * @return the boolean
     */
    public boolean isDeadly()
    {
        return deadly;
    }

    /**
     * Sets deadly.
     *
     * @param deadly the deadly
     */
    public void setDeadly(boolean deadly)
    {
        this.deadly = deadly;
    }

    @Contract(pure = true)
    private Animation getNormalAnimation()
    {
        return normalAnimation;
    }

    private void setNormalAnimation(Animation normalAnimation)
    {
        this.normalAnimation = normalAnimation;
    }

    @Contract(pure = true)
    private int getSpeed()
    {
        return speed;
    }

    private void setSpeed(int speed)
    {
        this.speed = speed;
    }

    @Contract(pure = true)
    private int getDamage()
    {
        return damage;
    }

    private void setDamage(int damage)
    {
        this.damage = damage;
    }
}
