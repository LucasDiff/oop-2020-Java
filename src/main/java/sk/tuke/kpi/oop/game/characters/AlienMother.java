

package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class AlienMother extends Alien {


    public AlienMother()
    {
        this(100, null);
    }

    public AlienMother(Behaviour<? super Alien> behaviour)
    {
        this(100, behaviour);
    }


    public AlienMother(int healthValue, Behaviour<? super Alien> behaviour)
    {
        super(healthValue, behaviour);
        this.setHealth(new Health(200));
        this.getHealth().onExhaustion(this::die);
        setAnimation(new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();

    }
}
