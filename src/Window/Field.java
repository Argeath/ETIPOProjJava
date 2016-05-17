package Window;

import Game.Organisms.Organism;
import Game.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Field extends JComponent {

    private World world;
    private Organism.OrganismType type;
    private int posX, posY;

    private boolean Hexagonal;

    Field(boolean hexagonal, int posX, int posY, World w) {
        super();
        this.type = Organism.OrganismType.NONE;
        this.Hexagonal = hexagonal;
        this.posX = posX;
        this.posY = posY;
        world = w;

        setPreferredSize(new Dimension(32, 32));

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(type == Organism.OrganismType.NONE) {
                    CreateOrganismMenu menu = new CreateOrganismMenu(posX, posY);
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public void setType(Organism.OrganismType t) {
        type = t;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Polygon p = new Polygon();
        if(Hexagonal) {
            p.addPoint(0, getHeight() / 2);
            p.addPoint(getWidth() / 4, 0);
            p.addPoint(3 * getWidth() / 4, 0);
            p.addPoint(getWidth(), getHeight() / 2);
            p.addPoint(3 * getWidth() / 4, getHeight());
            p.addPoint(getWidth() / 4, getHeight());
        } else {
            p.addPoint(0, 0);
            p.addPoint(getWidth(), 0);
            p.addPoint(getWidth(), getHeight());
            p.addPoint(0, getHeight());
        }
        g.setColor(Color.WHITE);
        g.fillPolygon(p);
        g.drawImage(MainFrame.images.get(type), 0, 0, null);
    }

    public Organism.OrganismType getType() {
        return type;
    }

    private class CreateOrganismMenu extends JPopupMenu {

        CreateOrganismMenu(int x, int y) {
            add(new JMenuItem(new CreateOrganismListener("Antelope", Organism.OrganismType.ANTELOPE, x, y)));
            add(new JMenuItem(new CreateOrganismListener("Fox", Organism.OrganismType.FOX, x, y)));
            add(new JMenuItem(new CreateOrganismListener("Grass", Organism.OrganismType.GRASS, x, y)));
            add(new JMenuItem(new CreateOrganismListener("Guarana", Organism.OrganismType.GUARANA, x, y)));
            add(new JMenuItem(new CreateOrganismListener("Sheep", Organism.OrganismType.SHEEP, x, y)));
            add(new JMenuItem(new CreateOrganismListener("SowThistle", Organism.OrganismType.SOW_THISTLE, x, y)));
            add(new JMenuItem(new CreateOrganismListener("Turle", Organism.OrganismType.TURTLE, x, y)));
            add(new JMenuItem(new CreateOrganismListener("Wolf", Organism.OrganismType.WOLF, x, y)));
            add(new JMenuItem(new CreateOrganismListener("Wolfberry", Organism.OrganismType.WOLFBERRY, x, y)));
        }

        private class CreateOrganismListener extends AbstractAction {
            private int posX, posY;
            private Organism.OrganismType type;

            CreateOrganismListener(String name, Organism.OrganismType t, int x, int y) {
                super(name);
                type = t;
                posX = x;
                posY = y;
            }

            public void actionPerformed(ActionEvent e) {
                world.createOrganism(type, posX, posY);
                world.render();
            }
        }
    }
}
