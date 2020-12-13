package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashMap;
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
    private Map<Input.Key, Direction> keyHistory;


    public MovableController(Movable actor) {
        this.actor = actor;
        keyHistory = new HashMap<>();
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        //System.out.println("R " + key.name());
        if (actor == null) {
            return;
        }
        if (!keyDirectionMap.containsKey(key)) {
            return;
        }
        if (action != null) {
            action.stop();
            //System.out.println("Stop R " + action.toString());
        }

        if (keyHistory.containsKey(key)) {
            keyHistory.remove(key);
            computeDirection();
        }
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        //System.out.println("P " + key.name());
        if (actor == null) {
            return;
        }

        if (!keyDirectionMap.containsKey(key)) {
            return;
        }

        if (action != null) {
            action.stop();
            //System.out.println("Stop P " + action.toString());
        }

        if (!keyHistory.containsKey(key)) {
            keyHistory.put(key, keyDirectionMap.get(key));
            computeDirection();
        }
    }

    private void computeDirection() {
        Direction dir = Direction.NONE;
        for (Input.Key key: keyHistory.keySet()) {
            dir = dir.combine(keyDirectionMap.get(key));
        }
        //System.out.println("C " + dir.name());
        if (dir != Direction.NONE) {
            action = new Move<>(dir, 100);
            action.scheduleFor(actor);
            //System.out.println("New Move " + action.toString());
        } else if (action != null) {
            action.stop();
            //System.out.println("Stop C " + action.toString());
        }
    }
}
