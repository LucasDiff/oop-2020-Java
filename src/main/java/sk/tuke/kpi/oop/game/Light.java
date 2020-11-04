package sk.tuke.kpi.oop.game;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
public class Light extends AbstractActor implements Switchable, EnergyConsumer{
    private boolean pripojene;
    private Animation lightOff = new Animation (   "sprites/light_off.png");
    private Animation lightOn = new Animation(  "sprites/light_on.png");
    private boolean zapnute;


    public Light(){
        this.updateAnimation();
    }
    public void setElectricityFlow(boolean pripojene){
        this.pripojene=pripojene;
        this.updateAnimation();
    }
    @Override
    public void turnOn() {
        this.zapnute = true;
        updateAnimation();
    }
    @Override
    public boolean zapnute() {
        return this.zapnute;
    }
    @Override
    public void turnOff() {
    this.zapnute = false;
    updateAnimation();
    }
    public void toggle() {
        int c = 0;
        if(this.zapnute && c == 0) {
            this.zapnute = false;
        }
        else{
            this.zapnute = true;
        }
    }

    @Override
    public void setPowered(boolean Powered) {this.setElectricityFlow(Powered);
    }
    public void updateAnimation() {
        if ( this.zapnute && this.pripojene) {
            this.setAnimation(this.lightOn);
        } else{this.setAnimation(this.lightOff);
        }
    }
}
