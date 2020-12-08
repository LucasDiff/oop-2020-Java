package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.openables.Door;

public class Button extends AbstractActor {
    public static final Topic<Button> BUTTON_WIN = Topic.create("button win", Button.class);

    public Button() {
        super("button");
        setAnimation(new Animation("sprites/button_red.png", 16, 16));

    }

    public void addedToScene(@NotNull Scene scene) {

        super.addedToScene(scene);

        //this.closed(scene);
        scene.getMessageBus().subscribeOnce(Door.DOOR_OPENED, a -> {
            opened(scene);
            scene.getMessageBus().publish(BUTTON_WIN, this);
        });
        //if (this.getBehaviour() != null) {
        // this.getBehaviour().setUp(this);
        //}
    }

    private void opened(Scene scene) {
        // scene.getMessageBus().subscribeOnce(Door.Door_open, a -> {
        setAnimation(new Animation("sprites/button_green.png", 16, 16));
        // });
    }
}
