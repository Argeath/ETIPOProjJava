package Game.Organisms;

import Game.World;
import Utils.InterruptedActionException;

import java.util.Random;

class Plant extends Organism {

    Plant(World _world) {
        super(_world);
    }

    @Override
    public void action() {
        Random rand = new Random();
        if(rand.nextInt(100) < 5)
            breed();
    }

    @Override
    void collision(Organism collider, boolean isAttacker) throws InterruptedActionException {

    }

    @Override
    void onDie() {

    }
}
