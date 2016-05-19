package Game;

import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import Game.Organisms.*;
import Utils.Direction;
import Window.GamePanel;

public class World implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient GamePanel gamePanel;

    private Dimension size;

    private Human human;

    private int round;

    public boolean finished = false;

    private Vector<Vector<Organism>> organisms;
    private Vector<Organism> toBorn;
    private Organism organismMap[][];

    public World(Dimension s) {
        size = s;
        setRound(0);

        organismMap = new Organism[size.height][size.width];

        for(int iy = 0; iy < size.height; iy++)
            for(int ix = 0; ix < size.width; ix++)
                organismMap[iy][ix] = null;

        organisms = new Vector<>();
        toBorn = new Vector<>();

        for(int i = 0; i <= 7; i++)
            organisms.add(new Vector<>());
    }

    public void init() {
        human = (Human)createOrganism(Organism.OrganismType.HUMAN);
        createRandomOrganisms();
    }

    private void createRandomOrganisms() {
        Random rand = new Random();
        int rozmiar = (int)Math.ceil(Math.sqrt((double)size.width * size.height) / 3);
        for(Organism.OrganismType type : Organism.OrganismType.values()) {
            if(type == Organism.OrganismType.HUMAN) continue;

            int amount = rand.nextInt(rozmiar);
            for(int i = 0; i < amount; i++)
                createOrganism(type);
        }
    }

    private void addOrganism(Organism organism) {
        if(getOrganismOnPos(organism.getPosition()) == null) {
            toBorn.add(organism);
            organismMap[organism.getPosition().height][organism.getPosition().width] = organism;
        }
    }

    private Organism createOrganism(Organism.OrganismType type) {
        Organism organism = Organism.getOrganismByType(this, type);
        if(organism == null) return null;
        Random r = new Random();
        int posX, posY, attempts = 0;
        do {
            posX = r.nextInt(getSize().width);
            posY = r.nextInt(getSize().height);
        } while (getOrganismOnPos(new Dimension(posX, posY)) != null || ++attempts >= 5);

        if(attempts >= 5) return null;

        return createOrganism(type, posX, posY);
    }

    public Organism createOrganism(Organism.OrganismType type, int x, int y) {
        Organism organism = Organism.getOrganismByType(this, type);
        if(organism == null) return null;
        organism.setPosition(new Dimension(x, y));

        addOrganism(organism);
        return organism;
    }

    public void update(int keyCode) {
        if(finished) return;

        for(Vector<Organism> v : organisms) {
            for(Organism organism : v) {
                if(organism.isDieing()) continue;

                organism.increaseAge();
                organism.action();

                if(organism.getType() == Organism.OrganismType.HUMAN)
                    ((Human)organism).handleInput(keyCode);
            }
        }

        for(Vector<Organism> v : organisms) {
            for (Iterator<Organism> iterator = v.iterator(); iterator.hasNext();) {
                Organism o = iterator.next();
                if (o.isDieing()) {
                    if(organismMap[o.getPosition().height][o.getPosition().width] == o)
                        organismMap[o.getPosition().height][o.getPosition().width] = null;
                    iterator.remove();
                }
            }
        }

        for(Organism organism : toBorn)
            organisms.elementAt(organism.getInitiative()).add(organism);

        toBorn.clear();

        render();
    }

    public void render() {
        for(int iy = 0; iy < size.height; iy++)
            for(int ix = 0; ix < size.width; ix++)
                if(organismMap[iy][ix] == null)
                    getGamePanel().fields[iy][ix].setType(Organism.OrganismType.NONE);
                else if(getGamePanel().fields[iy][ix].getType() != organismMap[iy][ix].getType())
                    getGamePanel().fields[iy][ix].setType(organismMap[iy][ix].getType());
    }

    public void save(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Organism getOrganismOnPos(Dimension pos) {
        return organismMap[pos.height][pos.width];
    }

    public void moveOrganism(Organism organism, Direction.D dir) {
        organismMap[organism.getPosition().height][organism.getPosition().width] = null;

        Dimension newPos = Direction.addDir(organism.getPosition(), dir);

        organism.setPosition(newPos);

        organismMap[newPos.height][newPos.width] = organism;
    }

    public Dimension getSize() {
        return size;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Human getHuman() {
        return human;
    }
}
