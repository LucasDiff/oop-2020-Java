package sk.tuke.kpi.oop.game.prototypeJava2;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.characters.Alien;

public class EnemyProduct extends AbstractActor implements EnemyPrototype {

    private EnemyProductType enemyProductType;
    private Alien alien;


    public EnemyProduct(EnemyProductType enemyProductType, Alien alien) {
        this.enemyProductType = enemyProductType;
        this.alien = alien;
    }

    @Override
    public EnemyPrototype createClone() {
        EnemyProduct clonedEnemyProduct = new EnemyProduct(this.enemyProductType, this.alien);
        return clonedEnemyProduct;
    }
}
