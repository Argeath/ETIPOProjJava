package Game.Organisms;

import Game.World;
import Utils.Direction;
import Window.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Human extends Animal {
    private int toNextSpell;

    Human(World _world) {
        super(_world);
        setType(OrganismType.HUMAN);
        setStrength(5);
        setInitiative(4);
    }

    @Override
    public void action() {}

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
        doSpell();
    }

    private void doSpell() {
        if (toNextSpell > 5)
        {
            for (int iy = -1; iy <= 1; iy++)
                for (int ix = -1; ix <= 1; ix++)
                {
                    if (iy == 0 && ix == 0) continue;
                    Dimension newPos = new Dimension(getPosition().width + ix, getPosition().height + iy);
                    if(newPos.width >= 0 && newPos.height >= 0 && newPos.width < getWorld().getSize().width && newPos.height < getWorld().getSize().height) {
                        Organism organism = getWorld().getOrganismOnPos(newPos);
                        if (organism != null) {
                            organism.setDieing(true);
                            MainFrame.logsArea.append(this + " burned " + organism + ".\n");
                        }
                    }
                }
        }

        if(toNextSpell > 0)
            toNextSpell--;
    }

    public void castSpell() {
        if(toNextSpell > 0) {
            MainFrame.logsArea.append("Spell will be active in " + toNextSpell + " rounds.\n");
            return;
        }
        toNextSpell = 10;
    }

    @Override
    void onDie() {
        getWorld().finished = true;
        JOptionPane.showMessageDialog(getWorld().getGamePanel(), "Koniec gry.");
    }

    public int getToNextSpell() {
        return toNextSpell;
    }

    public void setToNextSpell(int toNextSpell) {
        this.toNextSpell = toNextSpell;
    }
}
