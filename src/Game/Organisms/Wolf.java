package Game.Organisms;

import Game.World;

public class Wolf extends Animal {
    Wolf(World _world) {
        super(_world);
        setType(OrganismType.WOLF);
        setStrength(9);
        setInitiative(5);
    }
}
