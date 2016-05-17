package Game.Organisms;

import Game.World;
import Utils.InterruptedActionException;

public class Turtle extends Animal {
    Turtle(World _world) {
        super(_world);
        setType(OrganismType.TURTLE);
        setStrength(2);
        setInitiative(1);
    }

    @Override
    void collision(Organism collider, boolean isAttacker) throws InterruptedActionException {
        if(collider.getStrength() < 5)
            throw new InterruptedActionException();

        super.collision(collider, isAttacker);
    }
}
