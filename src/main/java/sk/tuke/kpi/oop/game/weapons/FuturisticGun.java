
package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Collectible;

public class FuturisticGun extends AbstractActor implements Collectible {
    public static final Topic<FuturisticGun> GUN_FOUND = Topic.create("gun found", FuturisticGun.class);

    public FuturisticGun() {
        super("Futuristic Gun");
        setAnimation(new Animation("sprites/futuristic_gun.png", 32, 32));
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        scene.getMessageBus().publish(GUN_FOUND, this);
    }
}
