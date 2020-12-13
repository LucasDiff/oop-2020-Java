


package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.characters.Armed;


public class FireController implements KeyboardListener {

    private Armed armed;

    public FireController(Armed armed) {
        this.armed = armed;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (key != Input.Key.SPACE) {
            return;
        }
        if (this.armed == null) {
            return;
        }

        new Fire<>().scheduleFor(this.armed);
    }


}
