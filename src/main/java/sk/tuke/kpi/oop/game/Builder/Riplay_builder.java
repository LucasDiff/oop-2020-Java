package sk.tuke.kpi.oop.game.Builder;

public class Riplay_builder {

    private String name;


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Riplay: " + this.name ;
    }
}
