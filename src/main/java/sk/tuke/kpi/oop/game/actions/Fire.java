package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

public class Fire<A extends Armed> extends AbstractAction<A> {

    @Override
    public void execute(float deltaTime)
    {
        if (getActor() == null) {
            setDone(true);
            return;
        }
        Scene scene = getActor().getScene();
        Fireable fireable = getActor().getFirearm().fire();
        if (fireable == null) {
            setDone(true);
            return;
        }

        scene.addActor(fireable, getActor().getPosX() + (16 * Direction.fromAngle(getActor().getAnimation().getRotation()).getDx()), getActor().getPosY() + (16 * Direction.fromAngle(getActor().getAnimation().getRotation()).getDy()));


        new Move<>(Direction.fromAngle(getActor().getAnimation().getRotation()), 100).scheduleFor(fireable);

        setDone(true);
    }
}
