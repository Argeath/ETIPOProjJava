package Game.Organisms;

import Game.World;
import Utils.InterruptedActionException;

public class Wolfberry extends Plant {
    Wolfberry(World _world) {
        super(_world);
        setType(OrganismType.WOLFBERRY);
        setStrength(999);
    }

    @Override
    void collision(Organism collider, boolean isAttacker) throws InterruptedActionException {
        collider.setDieing(true);
        setDieing(true);
    }
}
