package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;


public class Ventilator extends AbstractActor implements Repairable {

    private boolean broken;


    public Ventilator()
    {
        setAnimation(new Animation("sprites/ventilator.png", 32, 32, 0.1f, Animation.PlayMode.LOOP));
        getAnimation().stop();

        broken = true;
    }



    @Override
    public boolean repair()
    {
        if (broken) {
            broken = false;
            getAnimation().play();

            return true;
        }

        return false;
    }
}
