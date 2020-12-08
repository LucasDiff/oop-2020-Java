package sk.tuke.kpi.oop.game.prototype;

import java.util.HashMap;
import java.util.Map;

public class Factory {

    private Map<ProductType, Product> templates;

    public Factory() {
        this.templates = new HashMap<>();
        this.templates.put(ProductType.Victory, new Product(ProductType.Victory, "You won"));
        this.templates.put(ProductType.Defeat, new Product(ProductType.Defeat, "You not won"));
    }

    public Product createProduct(ProductType type) {
        return (Product) this.templates.get(type).createClone();
    }

}
