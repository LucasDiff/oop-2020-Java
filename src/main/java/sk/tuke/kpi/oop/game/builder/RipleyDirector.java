package sk.tuke.kpi.oop.game.builder;

public class RipleyDirector implements Director {

    @Override
    public void build(Builder builder) {
        builder.stopMove();

    }

}
