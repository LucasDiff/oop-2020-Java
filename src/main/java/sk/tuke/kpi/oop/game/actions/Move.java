package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move<M extends Movable> implements Action<M> {

    private M actor;

    private boolean firstTime;
    private boolean done;

    private Direction direction;

    private float duration;
    private float total;


    public Move(Direction direction) {
        this(direction, 0.0f);
    }

    public Move(Direction direction, float duration) {
        firstTime = true;
        this.direction = direction;
        this.duration = duration;
        done = false;
    }

    @Override
    public void execute(float deltaTime) {
        total += deltaTime;

        if (total >= duration) {
            stop();
            return;
        }

        if (getActor() == null) {
            return;
        }


        if (firstTime) {
            getActor().startedMoving(direction);
            firstTime = false;
        }

        int x = getActor().getPosX();
        int y = getActor().getPosY();

        int speed = actor.getSpeed();
        newPosition(x, y, speed);
        if (getActor().getScene().getMap().intersectsWithWall(getActor())) {
            getActor().collidedWithWall();

            if (getActor() != null) {
                getActor().setPosition(x, y);
            }

            stop();
        }
    }

    private void newPosition(int x, int y, int speed) {
        if (direction == Direction.NORTH) {
            actor.setPosition(x, y + speed);
        }
        if (direction == Direction.EAST) {
            actor.setPosition(x + speed, y);
        }
        if (direction == Direction.SOUTH) {
            actor.setPosition(x, y - speed);
        }
        if (direction == Direction.WEST) {
            actor.setPosition(x - speed, y);
        }
        if (direction == Direction.NORTHEAST) {
            actor.setPosition(x + speed, y + speed);
        }
        if (direction == Direction.NORTHWEST) {
            actor.setPosition(x - speed, y + speed);
        }
        if (direction == Direction.SOUTHEAST) {
            actor.setPosition(x + speed, y - speed);
        }
        if (direction == Direction.SOUTHWEST) {
            actor.setPosition(x - speed, y - speed);
        }

    }

    public void stop() {
        if (done) {
            return;
        }

        if (getActor() != null) {
            getActor().stoppedMoving();
        }
        done = true;
    }

    @Override
    public void reset() {
        firstTime = true;
        done = false;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Nullable
    @Override
    public M getActor() {
        return actor;
    }

    @Override
    public void setActor(@Nullable M actor) {
        this.actor = actor;
    }

    public Direction getDirection() {
        return direction;
    }
}
