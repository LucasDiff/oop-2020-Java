package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import java.util.Optional;


public class Alien extends AbstractActor implements Alive, Enemy, Movable {
    private Health health;

    public float getCounter() {
        return counter;
    }

    public void setCounter(float counter) {
        this.counter = counter;
    }

    private float counter;
    private Behaviour<? super Alien> behaviour;

    public static final Topic<Alien> ALIEN_BORN = Topic.create("alien born", Alien.class);
    public static final Topic<Alien> ALIEN_DIED = Topic.create("alien died", Alien.class);

    private static Alien instance;


    public static Alien getInstance() {
        if (instance == null)
            instance = new Alien();

        instance.setAnimation(new Animation("sprites/body.png", 64, 48));
        instance.stoppedMoving();
        return instance;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.deadly(scene);
        if (this.getBehaviour() != null) {
            this.getBehaviour().setUp(this);
        }
        scene.getMessageBus().publish(ALIEN_BORN, this);
    }

    public void die() {
        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }
        setCounter(getCounter() - 1);
        getAnimation().stop();
        getScene().getMessageBus().publish(ALIEN_DIED, this);

        scene.removeActor(this);
    }

    public Alien(int healthValue, Behaviour<? super Alien> behaviour) {

        this.setHealth(new Health(healthValue));
        this.setBehaviour(behaviour);
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();

        this.getHealth().onExhaustion(this::die);
    }

    public Alien() {
        this(100, null);
    }

    public Alien(Behaviour<? super Alien> behaviour) {
        this(1, behaviour);
    }

    @Override
    public Health getHealth() {
        return this.health;
    }


    public void setHealth(Health health) {
        this.health = health;
    }

    @Contract(pure = true)
    private Behaviour<? super Alien> getBehaviour() {
        return behaviour;
    }

    private void setBehaviour(Behaviour<? super Alien> behaviour) {
        this.behaviour = behaviour;
    }

    private void deadly(@NotNull Scene scene) {
        new When<>(
            action -> this.intersectsPlayer(scene).isPresent(),
            new Invoke<>(() -> {
                Optional<?> actor = this.intersectsPlayer(scene);
                if (!actor.isPresent()) {
                    return;
                }

                ((Alive) actor.get()).getHealth().drain(1);
                this.deadly(scene);
            })
        ).scheduleFor(this);
    }

    @NotNull
    private Optional<?> intersectsPlayer(@NotNull Scene scene) {
        return scene.getActors().stream()
            .filter(actor -> !(actor instanceof Enemy))
            .filter(actor -> !actor.equals(this))
            .filter(this::intersects)
            .filter(Alive.class::isInstance)
            .findFirst();
    }


    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public void startedMoving(Direction direction) {
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        getAnimation().stop();
    }
}
