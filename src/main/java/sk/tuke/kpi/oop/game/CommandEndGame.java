package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.GameApplication;

public class CommandEndGame implements Command<Actor> {

    private String message;

    public CommandEndGame(String message)
    {
        this.message = message;
    }

    @Override
    public void execute(Actor actor)
    {
        int y = actor.getScene().getGame().getWindowSetup().getHeight() - (GameApplication.STATUS_LINE_OFFSET * 2);

        actor.getScene().getGame().getOverlay()
            .drawText(message, 20, y)
            .showFor(5);
    }
}
