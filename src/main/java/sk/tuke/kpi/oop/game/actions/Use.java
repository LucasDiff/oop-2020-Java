package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;


public class Use<T extends Actor> extends AbstractAction<T> {

    private Usable<T> usable;


    public Use(Usable<T> a) {
        this.usable = a;
        this.setDone(false);
    }


    public Disposable scheduleForIntersectingWith(Actor mediatingActor) {
        Scene scene = mediatingActor.getScene();
        if (scene == null) return null;
        Class<T> usingActorClass = usable.getUsingActorClass();
        return scene.getActors().stream()
            .filter(mediatingActor::intersects)
            .filter(usingActorClass::isInstance)
            .map(usingActorClass::cast)
            .findFirst()
            .map(this::scheduleFor)
            .orElse(null);
    }

    @Override
    public void execute(float deltaTime) {
        usable.useWith(getActor());
        this.setDone(true);
    }

}
