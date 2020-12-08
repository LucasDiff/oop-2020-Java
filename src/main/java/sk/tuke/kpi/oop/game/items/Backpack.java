package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Backpack<A extends Actor> implements ActorContainer<Collectible> {
    private List<Collectible> arrayList;
    private String name;
    private int capacity;

    public Backpack(String name, int capacity) {
        this.name = name;
        arrayList = new ArrayList<>();
        this.capacity = capacity;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @NotNull
    @Override
    public List<Collectible> getContent() {
        return new ArrayList<>(this.arrayList);
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {

        return arrayList.size();
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if (this.getSize() >= this.getCapacity()) {
            throw new IllegalStateException(this.getName() + " is full");
        }
        this.arrayList.add(actor);
    }

    @Nullable
    @Override
    public Collectible peek() {
        if (this.arrayList.size() == 0) {
            return null;
        }
        return arrayList.get(getSize() - 1);
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        arrayList.remove(actor);
    }

    @Override
    public void shift() {

        Collections.rotate(arrayList, 1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return arrayList.iterator();
    }

}

