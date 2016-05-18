package Game.Organisms;

import Utils.Direction;
import Utils.InterruptedActionException;
import Game.World;
import Window.MainFrame;

import java.awt.*;

class Animal extends Organism {
    Animal(World _world) {
        super(_world);
    }

    @Override
    public void action() {
        if(isDieing()) return;

        Direction.D dir = getRandomDirection();
        if(dir == Direction.D.NONE) return;

        move(dir);
    }

    void move(Direction.D dir) {
        if(isDieing()) return;

        try {
            Dimension newPos = new Dimension(getPosition().width + Direction.toDim(dir).width, getPosition().height + Direction.toDim(dir).height);
            if(newPos.width >= 0 && newPos.height >= 0 && newPos.width < getWorld().getSize().width && newPos.height < getWorld().getSize().height) {
                Organism collider = getWorld().getOrganismOnPos(newPos);
                if (collider != null)
                    collision(collider, true);

                getWorld().moveOrganism(this, dir);
            }
        } catch(InterruptedActionException ignored) {}
    }

    @Override
    void collision(Organism collider, boolean isAttacker) throws InterruptedActionException {

        if(getType() == collider.getType() && getType() != OrganismType.HUMAN)
        {
            breed();
            throw new InterruptedActionException();
        }

        if(getType() == collider.getType()) // Bez kanibalizmu
            throw new InterruptedActionException();

        if(isAttacker)
            collider.collision(this);

        if(getStrength() > collider.getStrength() || isAttacker && getStrength() >= collider.getStrength()) {
            collider.setDieing(true);
            collider.onDie();

            MainFrame.logsArea.append(this + " killed " + collider + ".\n");
        } else if(getStrength() < collider.getStrength() && isAttacker) {
            setDieing(true);
            onDie();

            MainFrame.logsArea.append(this + " attacked " + collider + " and died.\n");
            throw new InterruptedActionException();
        }
    }

    @Override
    void onDie() {}
}
