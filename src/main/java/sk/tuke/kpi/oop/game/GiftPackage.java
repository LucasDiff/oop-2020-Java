

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class GiftPackage extends AbstractActor implements Wrappable {
    private Actor gift;
    private boolean wrapped;

    public GiftPackage() {
        super();
        this.setWrapped(true);
        setAnimation(new Animation("sprites/gift.png", 32, 32));
    }

    @Override
    public boolean unwrap() {
        if (!this.isWrapped()) {
            return false;
        }
        if (gift == null) {
            return false;
        }

        this.setWrapped(false);
        new CommandAddToScene(getScene(), getPosX(), getPosY()).execute(gift);
        getAnimation().stop();
        getScene().removeActor(this);

        return true;
    }

    @Contract(pure = true)
    private boolean isWrapped() {
        return wrapped;
    }

    private void setWrapped(boolean wrapped) {
        this.wrapped = wrapped;
    }

    public void setGift(Actor gift) { this.gift = gift; }
}
