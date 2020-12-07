package sk.tuke.kpi.oop.game.prototype_java_2;



public class Main {

    public static void main(String[] args) {

        // Create myFactory of products (myFactory is cloning chairs and tables)
        myFactory myFactory = new myFactory();

        // Create some chairs and tables
       // System.out.println(myFactory.createProduct(myProductType.KeyT));
       // System.out.println(myFactory.createProduct(myProductType.KeyT));
      //  System.out.println(myFactory.createProduct(myProductType.KeyT));
        System.out.println(myFactory.createProduct(myProductType.KeyY));

    }
}
