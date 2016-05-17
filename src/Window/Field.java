package Window;

import Game.Organisms.Organism;

import javax.swing.*;
import java.awt.*;

public class Field extends JComponent {

    private Organism.OrganismType type;

    Field() {
        super();
        setPreferredSize(new Dimension(32, 32));
    }

    void setType(Organism.OrganismType t) {
        type = t;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon p = new Polygon();
        p.addPoint(0, getHeight() / 2);
        p.addPoint(getWidth() / 4, 0);
        p.addPoint(3 * getWidth() / 4, 0);
        p.addPoint(getWidth(), getHeight() / 2);
        p.addPoint(3 * getWidth() / 4, getHeight());
        p.addPoint(getWidth() / 4, getHeight());

        g.setColor(Color.WHITE);
        g.fillPolygon(p);
        g.drawImage(MainFrame.images.get(type), 0, 0, null);
    }
}
