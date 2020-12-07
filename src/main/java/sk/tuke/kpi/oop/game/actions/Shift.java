package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Shift extends AbstractAction<Keeper<Collectible>> {

    @Override
    public void execute(float deltaTime)
    {
        if (getActor() != null) {
            getActor().getContainer().shift();
        }

        setDone(true);
    }
}
