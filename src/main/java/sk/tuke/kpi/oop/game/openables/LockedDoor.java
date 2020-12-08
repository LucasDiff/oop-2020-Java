package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.game.items.AccessCard;

public class LockedDoor extends Door {

    private boolean locked;

    public LockedDoor(String name, Orientation orientation) {
        super(name, orientation);

        locked = true;
    }

    @Override
    public void useWith(Actor actor) {
        if (actor == null) {
            return;
        }

        if (!(actor instanceof AccessCard)) {
            return;
        }

        if (locked) {
            unlock();
        }
    }

    @Override
    public void open() {
        if (locked) {
            return;
        }

        super.open();
    }

    public void unlock() {
        locked = false;
        open();
    }
}
