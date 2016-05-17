package Game.Organisms;

import Game.World;

public class SowThistle extends Plant {
    SowThistle(World _world) {
        super(_world);
        setType(OrganismType.SOW_THISTLE);
    }

    @Override
    public void action() {
        super.action();
        super.action();
        super.action();
    }
}
