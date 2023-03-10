package sk.tuke.kpi.oop.game.controllers;


import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Optional;


public class KeeperController implements KeyboardListener {
    private Keeper actor;

    public KeeperController(Keeper actor) {
        this.actor = actor;
        actor.getScene().getMessageBus().subscribe(Use.USABLE_USED, (a) -> removeFromBackpack(a));
    }


    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (key.equals(Input.Key.ENTER)) {
            new Take<>(Collectible.class).scheduleFor(actor);
        } else if (key.equals(Input.Key.BACKSPACE))
            new Drop<>().scheduleFor(actor);
        else if (key.equals(Input.Key.S)) {
            new Shift<>().scheduleFor(actor);
        } else if (key.equals(Input.Key.B)) {
            if (!(actor.getBackpack().peek() instanceof Usable)) {
                return;
            }
            if (actor.getBackpack().getSize() <= 0) {
                return;
            }
            Collectible collectible = actor.getBackpack().peek();
            new Use<>((Usable<?>) collectible).scheduleForIntersectingWith(actor);
        } else if (key.equals(Input.Key.U)) {
            Optional<Actor> usable = actor.getScene().getActors().stream().filter(Usable.class::isInstance).filter(actor::intersects).findFirst();
            usable.ifPresent(actor1 -> new Use<>((Usable<?>) actor1).scheduleForIntersectingWith(actor));

        }
    }

    private void removeFromBackpack(Actor actor) {
        if (!(actor instanceof Keeper)) {
            return;
        }

        Keeper keeper = (Keeper) actor;
        keeper.getBackpack().remove(keeper.getBackpack().peek());
    }
}


















