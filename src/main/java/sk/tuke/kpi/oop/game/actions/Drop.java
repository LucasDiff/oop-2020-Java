package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop<A extends Actor> extends AbstractAction<Keeper<A>> {

    @Override
    public void execute(float deltaTime)
    {
        if (getActor() == null) {
            setDone(true);
            return;
        }


        try {
            Backpack<A> backpack = getActor().getBackpack();
            if (backpack != null) {
                Collectible actor = backpack.peek();
                if (actor != null) {
                    backpack.remove(actor);
                    getActor().getScene().addActor(actor, getActor().getPosX(), getActor().getPosY());
                }
            }
        } catch (Exception ex) {
            setDone(true);
        }

        setDone(true);
    }
}
















