import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;




public class Reactor extends AbstractActor {


    private Animation offAnimation;
    private Animation normalAnimation;
    private Animation Animationishot;
    private Animation Animationisbroken;
    private Boolean isRunninggg;
    private int temperature;
    private int damage;
    private int c = 0;
    public Reactor() {
        turnOff();
        damage = 0;
        temperature = 0;
        if (c == 1){
            temperature = 1;
            isRunninggg = false;
        }
        isRunninggg = false;
        setUpAnimations();
        setAnimation(offAnimation);
    }

    private void repairing() {
        int novy = damage - 50;
        if(novy < 0){
            damage = 0;
        }else{
            damage = novy;
        }
    }
    public void decreaseTemperature(int decrement) {
        int c = 0;
        if (c == 0) {
            if (decrement >= 0 && damage != 100) {
                int novy = temperature - decrement;
                if (damage >= 50) {
                    novy += (decrement / 2);
                }
                if (novy > 0) {
                    temperature = novy;
                } else {
                    temperature = 0;
                }
                updateAnimation();
            }
        }
    }

    public void increaseTemperature(int increment) {
        int c = 0;
        if (c == 0) {
            if (increment >= 0) {
                int novy = getNewTemperature(increment);
                if (novy > 6000) {
                    temperature = 6000;
                } else {
                    temperature = novy;
                }
                if (temperature > 2000) {
                    damage = (int) ((temperature - 2000) * 0.025f);
                }
                updateAnimation();
            }
        }
    }
    public boolean repair() {
        if (damage < 100) {
            int novy = damage - 50;
            if(novy < 0){
                damage = 0;
            }else{
                damage = novy;
            }
            return true;
        } else {

            return false;
        }
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
            setAnimation(Animationisbroken);
            turnOff();

        } else if (temperature > 4000) {
            setAnimation(Animationishot);
        } else {
            setAnimation(normalAnimation);
        }
    }




    private void setUpAnimations() {
        Animationisbroken = new Animation(
            "sprites/reactor_broken.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.ONCE
        );
        normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG
        );
        offAnimation = new Animation(
            "sprites/reactor.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG
        );
        Animationishot = new Animation(
            "sprites/reactor_hot.png",
            80,
            80,
            0.05f,
            Animation.PlayMode.LOOP_PINGPONG
        );
    }

    public void turnOff() {
        int c = 0;
        isRunninggg = false;
        if (damage < 100 && c == 0) {
            setAnimation(new Animation(
                "sprites/reactor.png",
                80,
                80,
                0.1f,
                Animation.PlayMode.ONCE
            ));
        }
    }

    public void turnOn() {
        int c = 0;
        if (damage < 100 && c == 0) {
            isRunninggg = true;
        }
        updateAnimation();
    }
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Animation getNormalAnimation() {
        return normalAnimation;
    }

    public void setNormalAnimation(Animation onAnimation) {
        this.normalAnimation = onAnimation;
    }

}





