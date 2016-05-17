package Game.Organisms;

import Utils.InterruptedActionException;
import Game.World;

public class Fox extends Animal {
    Fox(World _world) {
        super(_world);
        setType(OrganismType.FOX);
        setStrength(3);
        setInitiative(7);
    }

    @Override
    void collision(Organism collider, boolean isAttacker) throws InterruptedActionException {
        if(isAttacker && collider.getStrength() > getStrength()) {
            action();
            throw new InterruptedActionException();
        }
        super.collision(collider, isAttacker);
    }
}
