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

        int posX = getActor().getPosX();
        int poxY = getActor().getPosY();
        Direction direction = Direction.fromAngle(getActor().getAnimation().getRotation());
        int dirDx = direction.getDx();
        int dirDy = direction.getDy();
        int x = posX + 8 + (24 * dirDx);
        int y = poxY + 8 + (24 * dirDy);
        scene.addActor(fireable, x, y);


        new Move<>(Direction.fromAngle(getActor().getAnimation().getRotation()), 100).scheduleFor(fireable);

        setDone(true);
    }
}
