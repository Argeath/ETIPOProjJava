package Game.Organisms;

import Utils.Direction;
import Game.World;
import Utils.InterruptedActionException;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public abstract class Organism implements Serializable {
    private static final long serialVersionUID = 2L;

    public enum OrganismType
    {
        HUMAN,
        WOLF,
        SHEEP,
        TURTLE,
        FOX,
        ANTELOPE,
        GRASS,
        GUARANA,
        WOLFBERRY,
        SOW_THISTLE,
        NONE
    };

    private World world;
    private OrganismType type;
    private int age;
    private int strength;
    private int initiative;
    private Dimension position;
    private boolean isDieing;


    Organism(World _world) {
        world = _world;
        setDieing(false);
        setAge(0);
        setStrength(0);
        setInitiative(0);
        setType(OrganismType.NONE);
    }

    public abstract void action();

    void collision(Organism collider) throws InterruptedActionException {
        collision(collider, false);
    }

    abstract void collision(Organism collider, boolean isAttacker) throws InterruptedActionException;

    abstract void onDie();

    Direction.D getRandomDirection() {
        return getRandomDirection(false);
    }

    Direction.D getRandomDirection(boolean mustBeEmpty) {
        Direction.D dirs[] = new Direction.D[4];
        int n = 0;

        for(Direction.D dir : Direction.D.values()) {
            if(dir == Direction.D.NONE) continue;
            Dimension newPos = Direction.addDir(position, dir);

            if(newPos.width >= 0 && newPos.height >= 0 && newPos.width < getWorld().getSize().width && newPos.height < getWorld().getSize().height
                && (!mustBeEmpty || getWorld().getOrganismOnPos(newPos) == null))
                    dirs[n++] = dir;
        }

        if(n == 0) return Direction.D.NONE;
        Random rand = new Random();
        return dirs[rand.nextInt(n)];
    }

    void breed() {
        Direction.D dir = getRandomDirection(true);
        if(dir == Direction.D.NONE) return;

        Organism organism = getOrganismByType(world, getType());
        if(organism == null) return;

        Dimension newPos = Direction.addDir(getPosition(), dir);

        world.createOrganism(getType(), newPos.width, newPos.height);
    }

    public static Organism getOrganismByType(World w, OrganismType type) {
        Organism organism = null;
        switch(type) {
            case HUMAN:
                organism = new Human(w);
                break;
            case ANTELOPE:
                organism = new Antelope(w);
                break;
            case FOX:
                organism = new Fox(w);
                break;
            case GRASS:
                organism = new Grass(w);
                break;
            case GUARANA:
                organism = new Guarana(w);
                break;
            case SHEEP:
                organism = new Sheep(w);
                break;
            case SOW_THISTLE:
                organism = new SowThistle(w);
                break;
            case TURTLE:
                organism = new Turtle(w);
                break;
            case WOLF:
                organism = new Wolf(w);
                break;
            case WOLFBERRY:
                organism = new Wolfberry(w);
                break;
        }
        return organism;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public OrganismType getType() {
        return type;
    }

    public void setType(OrganismType type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void increaseAge() {
        this.age++;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Dimension getPosition() {
        return position;
    }

    public void setPosition(Dimension position) {
        this.position = position;
    }

    public boolean isDieing() {
        return isDieing;
    }

    public void setDieing(boolean dieing) {
        isDieing = dieing;
    }

    @Override
    public String toString() {
        return getType().toString().toLowerCase();
    }
}
