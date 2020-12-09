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
    private int keyPressedCnt;
    private Input.Key[] pressedKeys = new Input.Key[2];

    public MovableController(Movable actor) {
        this.actor = actor;
        this.keyPressedCnt = 0;
        this.pressedKeys[0] = null;
        this.pressedKeys[1] = null;
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        System.out.println("KR: " + key.name());
        if (actor == null) {
            return;
        }
        if (!keyDirectionMap.containsKey(key)) {
            return;
        }
        if (action != null) {
            action.stop();

            if (keyPressedCnt == 2) {
                if (pressedKeys[0] == key) {
                    pressedKeys[0] = pressedKeys[1];
                }
                keyPressedCnt--; // as keyPressed increases the counter we need to compensate for it
                keyPressed(pressedKeys[0]);
            }
        }
        keyPressedCnt--;
        System.out.println("Keys Released: (" + keyPressedCnt + ") "+ (pressedKeys[0] == null ? "-" : pressedKeys[0].name()) + " " + (pressedKeys[1] == null ? "-" : pressedKeys[1].name()));
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        System.out.println("KP: " + key.name());
        if (actor == null) {
            return;
        }

        if (!keyDirectionMap.containsKey(key)) {
            return;
        }

        if (action != null) {
            action.stop();
        }
        if (keyPressedCnt < 2) {
            if (keyPressedCnt == 0 || pressedKeys[0] == key) {
                action = new Move<>(keyDirectionMap.get(key), 100);
                action.scheduleFor(actor);
                System.out.println("Priamy smer " + key.name());
            } else if (action.getDirection() != null && pressedKeys[0] != key) {
                Direction combinedDir = action.getDirection();
                combinedDir = combinedDir.combine(keyDirectionMap.get(key));
                if (combinedDir != Direction.NONE) {
                    action = new Move<>(combinedDir, 100);
                    action.scheduleFor(actor);
                    System.out.println("Kombinovany smer " + key.name() + " " + action.getDirection());
                }
            }
            pressedKeys[keyPressedCnt] = key;
        }
        keyPressedCnt++;
        System.out.println("Keys Pressed: (" + keyPressedCnt + ") "+ (pressedKeys[0] == null ? "-" : pressedKeys[0].name()) + " " + (pressedKeys[1] == null ? "-" : pressedKeys[1].name()));
    }


}
