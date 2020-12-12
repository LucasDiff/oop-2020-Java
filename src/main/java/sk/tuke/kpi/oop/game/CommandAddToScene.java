package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

public class CommandAddToScene implements Command<Actor> {

    private Scene scene;

    private int x;
    private int y;

    public CommandAddToScene(Scene scene, int x, int y) {
        this.x = x;
        this.y = y;
        this.scene = scene;

    }

    @Override
    public void execute(Actor actor) {
        scene.addActor(actor, x, y);
    }
}
