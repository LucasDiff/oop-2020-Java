package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController implements KeyboardListener {

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.LEFT, Direction.WEST)
    );

    private Movable actor;
    private Move<Movable> action;
    private Set<Input.Key> keyHistory;


    public MovableController(Movable actor) {
        this.actor = actor;
        keyHistory = new HashSet<>();
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if (actor == null) {
            return;
        }

        if (!keyDirectionMap.containsKey(key)) {
            return;
        }

        if (keyHistory.contains(key)) {
            keyHistory.remove(key);
            computeDirection();
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

        if (!keyHistory.contains(key)) {
            keyHistory.add(key);
            computeDirection();
        }
    }

    private void computeDirection() {
        if (action != null) {
            action.stop();
        }

        Direction dir = Direction.NONE;
        for (Input.Key key: keyHistory) {
            dir = dir.combine(keyDirectionMap.get(key));
        }

        if (dir != Direction.NONE) {
            action = new Move<>(dir, 100);
            action.scheduleFor(actor);
        }
    }
}
