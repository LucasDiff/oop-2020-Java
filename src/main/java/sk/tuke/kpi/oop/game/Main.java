package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Game;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.WindowSetup;
import sk.tuke.kpi.gamelib.World;
import sk.tuke.kpi.oop.game.scenarios.Map;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;

public class Main {

    public static void main(String[] args) {

        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);

        Game game = new GameApplication(windowSetup, new LwjglBackend());

        Scene scene = new World("world", "maps/map.tmx", new Map.Factory());

        scene.getInput().onKeyPressed(key -> {
            if (key == Input.Key.ESCAPE) {
                scene.getGame().stop();
            }
        });

        scene.addListener(new Map());

        game.addScene(scene);

        game.start();
    }
}
