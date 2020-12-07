package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;


public class Energy extends AbstractActor implements Usable<Alive>  {

    public Energy() {
        super("energy");
        setAnimation(new Animation("sprites/energy.png",16,16));
    }




    @Override
    public void useWith(Alive actor)
    {
        if (actor == null) {
            return;
        }

        actor.getHealth().refill(50);

       if(this.getScene()==null)return;


            this.getScene().removeActor(this);

    }



    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }




    }











































/*
public class Energy extends AbstractActor implements Usable<Ripley> {

   private int energy;

    public Energy() {
//this.energy=100;
        setAnimation(new Animation("sprites/energy.png", 16, 16));
    }



    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void useWith(Ripley actor) {
        actor.setEnergy(100);
    }
}*/

