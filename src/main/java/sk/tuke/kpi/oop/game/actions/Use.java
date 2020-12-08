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
        Class<T> usingActorClass = usable.getUsingActorClass();  // `usable` je spominana clenska premenna
        return scene.getActors().stream()  // ziskame stream actorov na scene
            .filter(mediatingActor::intersects)  // vyfiltrujeme actorov, ktori su v kolizii so sprostredkovatelom
            .filter(usingActorClass::isInstance) // vyfiltrujeme actorov kompatibilneho typu
            .map(usingActorClass::cast)  // vykoname pretypovanie streamu actorov
            .findFirst()  // vyberieme prveho (ak taky existuje) actora zo streamu
            .map(this::scheduleFor)  // zavolame metodu `scheduleFor` s najdenym actorom a vratime `Disposable` objekt
            .orElse(null);  // v pripade, ze ziaden vyhovujuci actor nebol najdeny, vratime `null`
    }

    @Override
    public void execute(float deltaTime) {
        usable.useWith(getActor());
        this.setDone(true);
    }

}
