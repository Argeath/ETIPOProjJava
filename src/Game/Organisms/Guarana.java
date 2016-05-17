package Game.Organisms;

import Game.World;
import Utils.InterruptedActionException;

public class Guarana extends Plant {
    Guarana(World _world) {
        super(_world);
        setType(OrganismType.GUARANA);
    }

    @Override
    void collision(Organism collider, boolean isAttacker) throws InterruptedActionException {
        collider.setStrength(collider.getStrength() + 3);
    }
}
