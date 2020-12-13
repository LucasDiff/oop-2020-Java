package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.builder.Message;

public class CommandEndGame implements Command<Actor> {

    private String message;
    private boolean winEnd;

    public CommandEndGame(String message, boolean winEnd) {
        this.message = message;
        this.winEnd = winEnd;
    }

    @Override
    public void execute(Actor actor) {
        Scene scene = actor.getScene();
        int y = scene.getGame().getWindowSetup().getHeight() - (GameApplication.STATUS_LINE_OFFSET * 3);

        scene.getGame().getOverlay()
            .drawText(message, 20, y)
            .showFor(5);
        int posX = actor.getPosX();
        int posY = actor.getPosY();

        if (winEnd) {
            FinalAnimation animation = new FinalAnimation();
            scene.addActor(animation, 432, 432);
            animation.start();
            scene.removeActor(actor);
            Message msg = new Message.MessageBuilder("You won Ripley. Congratulations!").setAddNewLines(true).build();
            msg.display(scene);
        } else {
            scene.removeActor(actor);
            LargeExplosion explosion = new LargeExplosion();
            scene.addActor(explosion, posX, posY);
            explosion.start();
            Message msg = new Message.MessageBuilder("You lost Ripley. Try once again.").setAddNewLines(true).build();
            msg.display(scene);
        }

    }
}
