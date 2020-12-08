package sk.tuke.kpi.oop.game.prototypeJava2;


import java.util.HashMap;
import java.util.Map;

public class MyFactory {
    private Map<MyProductType, MyProduct> templates;


    public MyFactory() {
        this.templates = new HashMap<>();
        sk.tuke.kpi.oop.game.characters.Alien alien = sk.tuke.kpi.oop.game.characters.Alien.getInstance();

        if (alien == null) return;
        this.templates.put(MyProductType.KeyY, new MyProduct(MyProductType.KeyY, alien));

    }


    public MyProduct createProduct(MyProductType type) {
        return (MyProduct) this.templates.get(type).createClone();
    }

}
