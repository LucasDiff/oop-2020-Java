package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;

public class Take<A extends Actor> extends AbstractAction<Keeper<A>> {

    private Class<A> takeableActorsClass;

    public Take(Class<A> takeableActorsClass)
    {
        this.takeableActorsClass = takeableActorsClass;
    }

    @Override
    public void execute(float deltaTime)
    {

        if (getActor() == null) {
            setDone(true);
            return;
        }

        Scene scene = getActor().getScene();
        scene.getActors().stream()

            .filter(actor -> actor.intersects(getActor()))
            .filter(actor -> takeableActorsClass.isInstance(actor))
            .findFirst().ifPresent(actor -> {

            try {
                ActorContainer<A> container = getActor().getContainer();
                if (container != null) {
                    container.add(takeableActorsClass.cast(actor));

                    scene.removeActor(takeableActorsClass.cast(actor));
                }
            } catch (Exception ex) {
                setDone(true);
            }
        });

        setDone(true);
    }
}
