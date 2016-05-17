package Game.Organisms;

import Utils.Direction;
import Utils.InterruptedActionException;
import Game.World;

import java.awt.*;

public class Animal extends Organism {
    Animal(World _world) {
        super(_world);
    }

    @Override
    public void action() {
        if(isDieing()) return;

        Direction.D dir = getRandomDirection();
        if(dir == Direction.D.NONE) return;

        try {
            Dimension newD = new Dimension(getPosition().width + Direction.toDim(dir).width, getPosition().height + Direction.toDim(dir).height);
            Organism collider = getWorld().getOrganismOnPos(newD);
            if(collider != null)
                collision(collider, true);

            getWorld().moveOrganism(this, dir);
        } catch(InterruptedActionException ignored) {}
    }

    @Override
    void collision(Organism collider, boolean isAttacker) throws InterruptedActionException {

    }

    @Override
    void onDie() {}
}
