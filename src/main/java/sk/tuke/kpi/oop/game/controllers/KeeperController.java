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
    private Keeper<Collectible> actor;

    public KeeperController(Keeper<Collectible> actor) {
        this.actor = actor;
    }


    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (key.equals(Input.Key.ENTER)) {
            new Take<>(Collectible.class).scheduleFor(actor);
        } else if (key.equals(Input.Key.BACKSPACE))
            new Drop<Collectible>().scheduleFor(actor);
        else if (key.equals(Input.Key.S)) {
            new Shift<Collectible>().scheduleFor(actor);
        } else if (key.equals(Input.Key.B)) {
            if (!(actor.getBackpack().peek() instanceof Usable)) {
                return;
            }
            if (actor.getBackpack().getSize() <= 0) {
                return;
            }
            new Use<>((Usable<?>) actor.getBackpack().peek()).scheduleOnIntersectingWith(actor);
        } else if (key.equals(Input.Key.U)) {
            Optional<Actor> usable = actor.getScene().getActors().stream().filter(Usable.class::isInstance).filter(actor::intersects).findFirst();
            usable.ifPresent(actor1 -> new Use<>((Usable<?>) actor1).scheduleOnIntersectingWith(actor));

        }
    }
}


















