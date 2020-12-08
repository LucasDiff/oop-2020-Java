package sk.tuke.kpi.oop.game.Pprototype;

public class Product implements Prototype {

    private ProductType productType;
    private String name;
    private String manufacturer;

    public Product(ProductType productType, String name) {
        this.productType = productType;
        this.name = name;
        this.manufacturer = "";
    }

    @Override
    public Prototype createClone() {
        Product clonedProduct = new Product(this.productType, this.name);
        return clonedProduct;
    }

    @Override
    public String toString() {
        return this.productType + ": " + this.name;
    }

}
