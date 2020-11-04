package tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer  extends BreakableTool {

    private Animation normalAnimation;

    public Hammer(int usesLeft) {
        super(usesLeft);
        setAnimation(new Animation(
            "sprites/hammer.png",
            16,
            16,
            0.1f,
            Animation.PlayMode.ONCE
        ));
    }
    public Hammer() {
        super(1);
        setAnimation(new Animation(
            "sprites/hammer.png",
            16,
            16,
            0.1f,
            Animation.PlayMode.ONCE
        ));
    }


}
