package Game.Organisms;

import Game.World;
import Utils.InterruptedActionException;

import java.util.Random;

public class Antelope extends Animal {
    Antelope(World _world) {
        super(_world);
        setType(OrganismType.ANTELOPE);
        setStrength(4);
        setInitiative(4);
    }

    @Override
    public void action() {
        super.action();
        super.action();
    }

    @Override
    void collision(Organism collider, boolean isAttacker) throws InterruptedActionException {
        if(!isAttacker && collider.getType() != getType()) {
            Random r = new Random();
            int rInt = r.nextInt(100);
            if(rInt < 50) {
                super.action();
                return;
            }
        }
        super.collision(collider, isAttacker);
    }
}
