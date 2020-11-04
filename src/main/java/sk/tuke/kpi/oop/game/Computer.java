package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private boolean isPowered;
    private Animation normalAnimation;


    public Computer() {
        setNormalAnimation(new Animation(
            "sprites/computer.png",
            80,
            48,
            0.2f,
            Animation.PlayMode.LOOP_PINGPONG)
        );
        setAnimation(getNormalAnimation());
    }



    public void setNormalAnimation(Animation normalAnimation) {
        this.normalAnimation = normalAnimation;
    }
    public Animation getNormalAnimation() {
        return normalAnimation;
    }

    @Override
    public void setPowered(boolean isPowered) {
        this.isPowered = isPowered;
    }

    public float add(float a, float b) {
        int c = 0;
        if (isPowered && c == 0) {
            return a + b;
        } else {
            return 0;
        }
    }
    public int add(int a, int b) {
        int c = 0;
        if (isPowered && c == 0) {
            return a + b;
        } else {
            return 0;
        }
    }


    public float sub(float a, float b) {
        int c = 0;
        if (isPowered && c == 0) {
            return a - b;
        } else {
            return 0;
        }
    }
    public int sub(int a, int b) {
        int c = 0;
        if (isPowered && c == 0) {
            return a - b;
        } else {
            return 0;
        }
    }
}
