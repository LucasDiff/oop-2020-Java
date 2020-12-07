package sk.tuke.kpi.oop.game.prototype_java_2;


import sk.tuke.kpi.gamelib.framework.AbstractActor;

public class myProduct extends AbstractActor implements MyPrototype {

    private myProductType myProductType ;
private sk.tuke.kpi.oop.game.characters.Alien alien;


    public myProduct(myProductType  myProductType , sk.tuke.kpi.oop.game.characters.Alien alien) {
        this.myProductType  = myProductType ;
        this.alien=alien;

       // this.posY = posY;
    }

    @Override
    public MyPrototype createClone() {
        myProduct clonedMyProduct = new myProduct(this.myProductType ,this.alien);
        return clonedMyProduct;
    }

   // @Override
   // public String toString() {
   //     return this.Alien + ": " + this.posX + ", " + this.posY;
    //}

}
