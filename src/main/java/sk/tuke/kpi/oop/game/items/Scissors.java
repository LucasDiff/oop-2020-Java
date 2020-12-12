
package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Wrappable;

public class Scissors extends CuttingTool<Wrappable> implements Collectible {

    public Scissors() {
        super(1);
        setAnimation(new Animation("sprites/scissors.png", 16, 16));
    }

    @Override
    public void useWith(Wrappable actor) {
        if (actor == null) {
            return;
        }

        if (actor.unwrap()) {
            setSharpnessLevel(getSharpnessLevel() - 1);
            if (getSharpnessLevel() <= 0) {
                getScene().removeActor(this);
            }
        }
    }

    @Override
    public Class<Wrappable> getUsingActorClass() {
        return Wrappable.class;
    }
}
