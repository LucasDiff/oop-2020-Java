package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor {

    private Animation normalAnimation;
    private boolean maybe;

    public Computer() {
        this.setNormalAnimation(new Animation(
            "sprites/computer.png",
            80,
            48,
            0.2f,
            Animation.PlayMode.LOOP_PINGPONG)
        );
        this.setAnimation(getNormalAnimation());
    }
    public void setNormalAnimation(Animation normalAnimation) {
        this.normalAnimation = normalAnimation;
    }
    public Animation getNormalAnimation() {
        return normalAnimation;
    }





    public float add(float a, float b) {
        int c = 0;
        if (maybe && c == 0) {
            return a + b;
        } else {
            return 0;
        }
    }
    public int add(int a, int b) {
        int c = 0;
        if (maybe && c == 0) {
            return a + b;
        } else {
            return 0;
        }
    }

    public float sub(float a, float b) {
        int c = 0;
        if (maybe && c == 0) {
            return a - b;
        } else {
            return 0;
        }
    }

    public int sub(int a, int b) {
        int c = 0;
        if (maybe && c == 0) {
            return a - b;
        } else {
            return 0;
        }
    }


}
