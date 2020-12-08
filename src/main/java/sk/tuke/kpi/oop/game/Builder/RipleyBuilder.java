package sk.tuke.kpi.oop.game.builder;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class RipleyBuilder extends AbstractActor implements Builder {
    private Ripley ripley;

    public RipleyBuilder() {
        if (this.ripley == null) return;
        if (getScene() == null) return;
        this.ripley = ripley.getScene().getFirstActorByType(Ripley.class);
    }

    @Override
    public void stopMove() {
        if (this.ripley == null) return;
        this.ripley.stoppedMoving();
    }

    @Override
    public Ripley getBuilderName() {
        return ripley;
    }


}
