package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.Door;

public class Win extends AbstractActor implements EnergyConsumer, Switchable {

    private boolean on;

   private final Animation offAnimation = new Animation("sprites/money.png", 16, 16);
    private final Animation onAnimation  = new Animation("sprites/won.png", 100, 100);


    public Win()
    {  setAnimation(offAnimation);
        on = false;
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);


        scene.getMessageBus().subscribeOnce(Button.Button_win, a -> {
            turnOn();
    });
    }

    @Override
    public void turnOn()
    {
        setAnimation(onAnimation);
        on = true;


    }

    @Override
    public void turnOff()
    {
        setAnimation(offAnimation);
        on = false;
    }

    @Override
    public void setPowered(boolean powered)
    {
        if (powered) {
            turnOn();
        } else {
            turnOff();
        }
    }

    @Override
    public boolean isOn()
    {
        return on;
    }
}
