package Game.Organisms;

import Game.World;

public class Sheep extends Animal {
    Sheep(World _world) {
        super(_world);
        setType(OrganismType.SHEEP);
        setStrength(4);
        setInitiative(4);
    }
}
