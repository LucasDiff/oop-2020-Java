package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;


public class Use<T extends Actor> extends AbstractAction<T> {
    public static final Topic<Actor> USABLE_USED = Topic.create("usable used", Actor.class);

    private Usable<T> usable;
    private Actor mediatingActor;


    public Use(Usable<T> a) {
        this.usable = a;
        this.setDone(false);
    }


    public Disposable scheduleForIntersectingWith(Actor mediatingActor) {
        this.mediatingActor = mediatingActor;
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
        if (usable.oneTimeUsable()) {
            getActor().getScene().getMessageBus().publish(USABLE_USED, mediatingActor);
        }
        this.setDone(true);
    }

}
