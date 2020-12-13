package sk.tuke.kpi.oop.game;

import java.util.Random;

public enum Direction {
    EAST(1, 0),
    NONE(0, 0),
    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    NORTHEAST(1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHWEST(-1, 1);


    private final int dx;
    private final int dy;


    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public static Direction fromAngle(float angle) {
        if (angle == 0.0f) {
            return NORTH;
        } else if (angle == 270.0f) {
            return EAST;
        } else if (angle == 180.0f) {
            return SOUTH;
        } else if (angle == 90.0f) {
            return WEST;
        } else if (angle == 315.0f) {
            return NORTHEAST;
        } else if (angle == 45.0f) {
            return NORTHWEST;
        } else if (angle == 225.0f) {
            return SOUTHEAST;
        } else if (angle == 135.0f) {
            return SOUTHWEST;
        }

        return NONE;
    }


    public float getAngle() {

        switch (this) {
            case NORTH:
                return 0f;
            case EAST:
                return 270f;
            case SOUTH:
                return 180f;
            case WEST:
                return 90f;
            case NORTHEAST:
                return 315f;
            case NORTHWEST:
                return 45f;
            case SOUTHWEST:
                return 135f;
            case SOUTHEAST:
                return 225f;
            default:
                break;
        }
        return 0;

    }

    public static Direction random() {
        return (Direction.values())[(new Random()).nextInt(Direction.values().length)];
    }


    public Direction combine(Direction other) {
        if (other == null) {
            return this;
        }

        int nDx = (this.getDx() + other.getDx() > 1) ? 1 : this.getDx() + other.getDx();
        nDx = (nDx < -1) ? -1 : nDx;

        int nDy = (this.getDy() + other.getDy() > 1) ? 1 : this.getDy() + other.getDy();
        nDy = (nDy < -1) ? -1 : nDy;

        for (Direction direction : Direction.values()) {
            if (nDx != direction.getDx()) {
                continue;
            }

            if (nDy != direction.getDy()) {
                continue;
            }

            return direction;
        }

        return Direction.NONE;
    }


}



























