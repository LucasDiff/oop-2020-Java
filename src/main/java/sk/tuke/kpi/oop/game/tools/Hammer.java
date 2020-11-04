package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Repairable;

public class Hammer extends BreakableTool<Repairable>  {

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
