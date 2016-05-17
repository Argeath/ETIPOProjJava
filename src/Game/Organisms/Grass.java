package Game.Organisms;

import Game.World;

public class Grass extends Plant {
    Grass(World _world) {
        super(_world);
        setType(OrganismType.GRASS);
    }
}
