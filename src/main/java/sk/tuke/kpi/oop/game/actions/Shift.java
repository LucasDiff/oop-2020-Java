package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Shift<A extends Actor> extends AbstractAction<Keeper<A>> {

    @Override
    public void execute(float deltaTime)
    {
        if (getActor() != null) {
            getActor().getContainer().shift();
        }

        setDone(true);
    }
}
