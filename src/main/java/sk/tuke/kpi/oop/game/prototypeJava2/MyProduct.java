package sk.tuke.kpi.oop.game.prototypeJava2;


import sk.tuke.kpi.gamelib.framework.AbstractActor;

public class MyProduct extends AbstractActor implements MyPrototype {

    private MyProductType myProductType;
    private sk.tuke.kpi.oop.game.characters.Alien alien;


    public MyProduct(MyProductType myProductType, sk.tuke.kpi.oop.game.characters.Alien alien) {
        this.myProductType = myProductType;
        this.alien = alien;

        // this.posY = posY;
    }

    @Override
    public MyPrototype createClone() {
        MyProduct clonedMyProduct = new MyProduct(this.myProductType, this.alien);
        return clonedMyProduct;
    }

    // @Override
    // public String toString() {
    //     return this.Alien + ": " + this.posX + ", " + this.posY;
    //}

}
