package Game;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import Game.Organisms.*;
import Utils.Direction;

public class World {
    private Dimension size;

    private int round;

    private Vector<Vector<Organism>> organisms;
    private Vector<Organism> toBorn;
    private Organism organismMap[][];


    public World() {
        setSize(new Dimension(20, 20));
        setRound(0);

        organismMap = new Organism[size.height][size.width];

        for(int iy = 0; iy < size.height; iy++)
            for(int ix = 0; ix < size.width; ix++)
                organismMap[iy][ix] = null;

        organisms = new Vector<>();
        toBorn = new Vector<>();

        for(int i = 0; i <= 7; i++) {
            organisms.add(new Vector<>());
        }
    }

    void init() {
        createOrganism(Organism.OrganismType.HUMAN);
        createRandomOrganisms();
    }

    private void createRandomOrganisms() {
        Random rand = new Random();
        int rozmiar = (int)Math.ceil(Math.sqrt((double)size.width * size.height) / 5);
        for(Organism.OrganismType type : Organism.OrganismType.values()) {
            int amount = rand.nextInt(rozmiar) + rozmiar / 2;
            for(int i = 0; i < amount; i++)
                createOrganism(type);
        }
    }

    void addOrganism(Organism organism) {
        if(getOrganismOnPos(organism.getPosition()) == null) {
            toBorn.add(organism);
            organismMap[organism.getPosition().height][organism.getPosition().width] = organism;
        }
    }

    Organism createOrganism(Organism.OrganismType type) {
        Organism organism = Organism.getOrganismByType(this, type);
        if(organism == null) return null;
        Random r = new Random();
        int posX = r.nextInt(getSize().width);
        int posY = r.nextInt(getSize().height);

        organism.setPosition(new Dimension(posX, posY));

        addOrganism(organism);
        return organism;
    }

    void update(int keyCode) {
        for(Vector<Organism> v : organisms) {
            for(Organism organism : v) {
                if(organism.isDieing()) continue;

                organism.increaseAge();
                organism.action();

                if(organism.getType() == Organism.OrganismType.HUMAN) {
                    ((Human)organism).handleInput(keyCode);
                }

            }
        }

        for(Vector<Organism> v : organisms) {
            for (Iterator<Organism> iterator = v.iterator(); iterator.hasNext();) {
                Organism o = iterator.next();
                if (o.isDieing()) {
                    iterator.remove();
                }
            }
        }

        for(Organism organism : toBorn) {
            organisms.elementAt(organism.getInitiative()).add(organism);
        }
        toBorn.clear();

        render();
    }

    void render() {

    }

    void save() {

    }

    public Organism getOrganismOnPos(Dimension pos) {
        return organismMap[pos.height][pos.width];
    }

    public void moveOrganism(Organism organism, Direction.D dir) {
        organismMap[organism.getPosition().height][organism.getPosition().width] = null;

        Dimension newD = Direction.addDir(organism.getPosition(), dir);

        organism.setPosition(newD);

        organismMap[newD.height][newD.width] = organism;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
