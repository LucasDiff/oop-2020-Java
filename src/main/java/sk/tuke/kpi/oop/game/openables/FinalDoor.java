package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.CommandAddToScene;
import sk.tuke.kpi.oop.game.characters.MotherAlien;
import sk.tuke.kpi.oop.game.items.FinalAccessCard;

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

        if (!(actor instanceof FinalAccessCard)) {
            return;
        }

        open();
    }

    @Override
    public void open() {
        super.open();

        if (!monsterReleased) {
            releaseMonster();
        }
    }

    public void releaseMonster() {
        monsterReleased = true;
        MotherAlien motherAlien = new MotherAlien(200);
        new CommandAddToScene(getScene(), 319, 272).execute(motherAlien);
    }
}
