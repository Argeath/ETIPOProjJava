package Game.Organisms;

import Game.World;
import Utils.InterruptedActionException;

public class Plant extends Organism {

    Plant(World _world) {
        super(_world);
    }

    @Override
    public void action() {

    }

    @Override
    void collision(Organism collider, boolean isAttacker) throws InterruptedActionException {

    }

    @Override
    void onDie() {

    }
}
