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


    public Disposable scheduleOnIntersectingWith(Actor mediatingActor) {
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































    /*



  package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<T extends Actor> extends AbstractAction<T> {
    private Usable<T> usable;
    private T actor;

    public Use(Usable<T> usable) {
        this.usable = usable;
    }

    @Override
    public void execute(float deltaTime) {
        if(usable != null && getActor() != null) {
            usable.useWith(getActor());
            setDone(true);
        }
    }

    public Disposable scheduleOnIntersectingWith(Actor mediatingActor) {
        Scene scene = mediatingActor.getScene();
        if(scene != null) {
            Class<T> usingActorClass = usable.getUsingActorClass();
            return scene.getActors().stream()
                .filter(mediatingActor::intersects)
                .filter(usingActorClass::isInstance)
                .map(usingActorClass::cast)
                .findFirst()
                .map(this::scheduleOn)
                .orElse(null);
        }
        return null;
    }

    @NotNull
    @Override
    public Disposable scheduleOn(T actor) {
        setActor(actor);
        return super.scheduleOn(getActor());
    }

    @Nullable
    @Override
    public T getActor() {
        return actor;
    }

    @Override
    public void setActor(T actor) {
        this.actor = actor;
    }
}

*/
