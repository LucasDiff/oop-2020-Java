package tools;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Point;

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
