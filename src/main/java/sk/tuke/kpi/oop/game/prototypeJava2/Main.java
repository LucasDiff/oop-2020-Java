package sk.tuke.kpi.oop.game.prototypeJava2;



public class Main {

    public static void main(String[] args) {

        // Create myFactory of products (myFactory is cloning chairs and tables)
        MyFactory myFactory = new MyFactory();

        // Create some chairs and tables
       // System.out.println(myFactory.createProduct(myProductType.KeyT));
       // System.out.println(myFactory.createProduct(myProductType.KeyT));
      //  System.out.println(myFactory.createProduct(myProductType.KeyT));
        System.out.println(myFactory.createProduct(MyProductType.KeyY));

    }
}
