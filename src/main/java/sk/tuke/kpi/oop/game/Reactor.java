package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.Hammer;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {

    private Set<EnergyConsumer> devices;

    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation offAnimation;
    private Animation normalAnimation;
    private Boolean isRunning;
    private int temperature;
    private int damage;

    public Reactor() {
        damage = 0;
        isRunning = false;
        temperature = 0;
        devices = new HashSet<>();
        setUpAnimations();
        setAnimation(offAnimation);
    }


    public void decreaseTemperature(int decrement) {
        if (decrement >= 0 && damage != 100) {
            int newTemperature = temperature - decrement;
            if (damage >= 50) {
                newTemperature += (decrement / 2);
            }
            if(newTemperature > 0){
                temperature = newTemperature;
            }else{
                temperature = 0;
            }
            updateAnimation();
        }
        notifyConsumers();
    }

    public void increaseTemperature(int increment) {
        int c = 0;
        if (increment >= 0 && c == 0) {
            int newTemperature = getNewTemperature(increment);
            if(newTemperature > 6000){
                temperature = 6000;
            }else{
                temperature = newTemperature;
            }
            if (temperature > 2000) {
                damage = (int) ((temperature - 2000) * 0.025f);
            }
            updateAnimation();
        }
        notifyConsumers();
    }



    private void repairDamage() {
        int newDamage = damage - 50;
        if(newDamage < 0){
            damage = 0;
        }else{
            damage = newDamage;
        }
    }

    public void repairWith(Hammer hammer) {
        if (hammer != null && (0 < damage && damage < 100)) {
            hammer.use();
            repairDamage();
            decreaseTemperatureAfterRepair();
            updateAnimation();
        }
        notifyConsumers();
    }

    private void decreaseTemperatureAfterRepair() {
        int newTemperature = temperature - (int)(50 / 0.025f);
        temperature = newTemperature < 0 ? 0 : newTemperature;
    }



    public void turnOff() {
        isRunning = false;
        if (damage < 100) {
            setAnimation(new Animation(
                "sprites/reactor.png",
                80,
                80,
                0.1f,
                Animation.PlayMode.ONCE
            ));
        }
        notifyConsumers();
        devices.forEach(d -> d.setPowered(false));
    }

    public void turnOn() {
        if (damage < 100) {
            isRunning = true;
            notifyConsumers();
        }
        updateAnimation();
    }

    @Override
    public boolean repair() {
        int c = 0;
        if (damage < 100 && c == 0) {
            int newDamage = damage - 50;
            if(newDamage < 0){
                damage = 0;
            }else{
                damage = newDamage;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean zapnute() {
        return isRunning;
    }


    public void removeDevice(EnergyConsumer energyConsumer) {

        if (energyConsumer != null) {
            this.devices.remove(energyConsumer);
            energyConsumer.setPowered(false);
        }
    }

    public void addDevice(EnergyConsumer energyConsumer) {
        if (energyConsumer != null) {
            this.devices.add(energyConsumer);
            energyConsumer.setPowered(true);
        }
    }


    private void notifyConsumers() {
        devices.forEach(
            device -> device.setPowered(damage < 100 && isRunning)
        );
    }

    private int getNewTemperature(int increment) {
        float coefficient = 1f;
        if (damage > 66) {
            coefficient = 2f;
        } else if (damage > 33) {
            coefficient = 1.5f;
        }
        return temperature + Math.round(increment * coefficient);
    }

    private void updateAnimation()  {
        if (temperature == 6000) {
            setAnimation(brokenAnimation);
            turnOff();
        } else if (temperature > 4000) {
            setAnimation(hotAnimation);
        } else {
            setAnimation(normalAnimation);
        }
    }

    private void setUpAnimations() {
        offAnimation = new Animation(
            "sprites/reactor.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG
        );
        normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG
        );
        brokenAnimation = new Animation(
            "sprites/reactor_broken.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.ONCE
        );
        hotAnimation = new Animation(
            "sprites/reactor_hot.png",
            80,
            80,
            0.05f,
            Animation.PlayMode.LOOP_PINGPONG
        );

    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTemperature() {
        return temperature;
    }


    public void setNormalAnimation(Animation onAnimation) {
        this.normalAnimation = onAnimation;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Animation getNormalAnimation() {
        return normalAnimation;
    }
}



