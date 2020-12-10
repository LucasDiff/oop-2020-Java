package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Monster;
import sk.tuke.kpi.oop.game.items.AccessCard;

public class FinalDoor extends Door {

    private boolean monsterReleased;

    public FinalDoor(String name, Orientation orientation) {
        super(name, orientation);
        monsterReleased = false;

        if (orientation == Orientation.VERTICAL) {
            setAnimation(new Animation("sprites/vdoor_blue.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        } else {
            setAnimation(new Animation("sprites/hdoor_blue.png", 32, 16, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        }
    }

    @Override
    public void useWith(Actor actor) {
        if (actor == null) {
            return;
        }

        if (!(actor instanceof AccessCard)) {
            return;
        }

        open();
        if (!monsterReleased) {
            releaseMonster();
        }
    }

    public void releaseMonster() {
        monsterReleased = true;
        Scene scene = getScene();
        Monster monster = new Monster(100, new RandomlyMoving());
        monster.addedToScene(scene);
    }
}
