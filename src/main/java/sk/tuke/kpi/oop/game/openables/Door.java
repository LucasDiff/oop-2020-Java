package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Usable<Actor>, Openable {

    private boolean open;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    private Orientation orientation;

    public Door(String name, Orientation orientation)
    {

        super(name);

        if (orientation == Orientation.VERTICAL) {
            setAnimation(new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        } else {
            setAnimation(new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        }

        this.orientation = orientation;
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        close();
    }

    @Override
    public void useWith(Actor actor)
    {
        if (!open) {
            this.open();
        }
    }

    @Override
    public void open()
    {
        this.getScene().getMessageBus().publish(DOOR_OPENED, this);

        open = true;

        this.getAnimation().setPlayMode(Animation.PlayMode.ONCE);
        this.getAnimation().play();

        Scene scene = getScene();
        if (orientation == Orientation.VERTICAL) {
            scene.getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.CLEAR);
            scene.getMap().getTile(this.getPosX() / 16, this.getPosY() / 16 + 1).setType(MapTile.Type.CLEAR);
        } else {
            scene.getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.CLEAR);
            scene.getMap().getTile(this.getPosX() / 16 + 1, this.getPosY() / 16).setType(MapTile.Type.CLEAR);
        }
    }

    @Override
    public void close()
    {

        open = false;



       if (orientation == Orientation.VERTICAL) {
           this.getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
           this.getAnimation().play();

       } else {
           this.getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
           this.getAnimation().play();

       }

        MapTile tile1 = getScene().getMap().getTile(getPosX() / 16, getPosY() / 16);
        MapTile tile2 = orientation == Orientation.VERTICAL ? (getScene().getMap().getTile(getPosX() / 16, getPosY() / 16 + getHeight() / 32)) : (getScene().getMap().getTile(getPosX() / 16 + getWidth() / 32, getPosY() / 16));

        tile1.setType(MapTile.Type.WALL);
        tile2.setType(MapTile.Type.WALL);





    }

    @Override
    public boolean isOpen()
    {
        return this.open;
    }

    @Override
    public Class<Actor> getUsingActorClass()
    {
        return Actor.class;
    }
}
