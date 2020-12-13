package sk.tuke.kpi.oop.game.prototypeJava2;


import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AngelGhost;

import java.util.HashMap;
import java.util.Map;

public class EnemyFactory {
    private Map<EnemyProductType, EnemyProduct> templates;


    public EnemyFactory() {
        this.templates = new HashMap<>();
        Alien alien = Alien.getInstance();
        AngelGhost angelGhost = new AngelGhost(50, new RandomlyMoving());

        if (alien == null) return;
        this.templates.put(EnemyProductType.BASE_ALIEN, new EnemyProduct(EnemyProductType.BASE_ALIEN, alien));
        if (angelGhost == null) return;
        this.templates.put(EnemyProductType.ANGEL_GHOST, new EnemyProduct(EnemyProductType.ANGEL_GHOST, angelGhost));

    }


    public EnemyProduct createProduct(EnemyProductType type) {
        return (EnemyProduct) this.templates.get(type).createClone();
    }

}
