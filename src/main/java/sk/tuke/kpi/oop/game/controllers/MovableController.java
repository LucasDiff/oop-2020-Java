package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Map;

public class MovableController implements KeyboardListener {

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.LEFT, Direction.WEST)
    );

    private Movable actor;
    private Move<Movable> action;

    public MovableController(Movable actor) {
        this.actor = actor;
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if (actor == null) {
            return;
        }
        if (!keyDirectionMap.containsKey(key)) {
            return;
        }
        if (action != null) {
            action.stop();
        }
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (actor == null) {
            return;
        }

        if (!keyDirectionMap.containsKey(key)) {
            return;
        }

        if (action != null) {
            action.stop();
        }
        action = new Move<>(keyDirectionMap.get(key), 100);
        action.scheduleFor(actor);
    }


}
