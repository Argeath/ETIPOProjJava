package Game.Organisms;

import Game.World;
import Utils.Direction;
import Utils.InterruptedActionException;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Human extends Animal {
    private int toNextSpell;

    public Human(World _world) {
        super(_world);
        setType(OrganismType.HUMAN);
        setStrength(5);
        setInitiative(4);
    }

    @Override
    public void action() {

    }

    public void handleInput(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_UP:
                move(Direction.D.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                move(Direction.D.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                move(Direction.D.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                move(Direction.D.EAST);
                break;
        }
    }

    public void castSpell() {
        if(toNextSpell > 0) {
            // TODO: ADD TO LOG
            return;
        }
        toNextSpell = 10;
    }

    @Override
    void onDie() {
        // TODO: FINISH GAME
    }

    public int getToNextSpell() {
        return toNextSpell;
    }

    public void setToNextSpell(int toNextSpell) {
        this.toNextSpell = toNextSpell;
    }
}
