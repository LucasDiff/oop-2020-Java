package sk.tuke.kpi.oop.game;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
public class Light extends AbstractActor implements Switchable, EnergyConsumer{
    private boolean pripojene;
    private Animation lightOff = new Animation (   "sprites/light_off.png");
    private Animation lightOn = new Animation(  "sprites/light_on.png");
    private boolean isOn;


    public Light(){
        this.updateAnimation();
    }
    public void setElectricityFlow(boolean pripojene){
        this.pripojene=pripojene;
        this.updateAnimation();
    }
    @Override
    public void turnOn() {
        this.isOn = true;
        updateAnimation();
    }
    @Override
    public boolean isOn() {
        return this.isOn;
    }
    @Override
    public void turnOff() {
    this.isOn = false;
    updateAnimation();
    }
    public void toggle() {
        int c = 0;
        if(this.isOn && c == 0) {
            this.isOn = false;
        }
        else{
            this.isOn = true;
        }
    }

    @Override
    public void setPowered(boolean Powered) {this.setElectricityFlow(Powered);
    }
    public void updateAnimation() {
        if ( this.isOn && this.pripojene) {
            this.setAnimation(this.lightOn);
        } else{this.setAnimation(this.lightOff);
        }
    }
}
