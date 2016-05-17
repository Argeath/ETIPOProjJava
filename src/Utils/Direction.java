package Utils;

import java.awt.*;

public class Direction {

    public enum D {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        NONE
    }

    public static Dimension toDim(D dir) {
        switch(dir) {
            case NORTH:
                return new Dimension(0, -1);
            case SOUTH:
                return new Dimension(0, 1);
            case EAST:
                return new Dimension(1, 0);
            case WEST:
                return new Dimension(-1, 0);
            default:
                return new Dimension(0, 0);
        }
    }

    public static Dimension addDir(Dimension dim, D dir) {
        return new Dimension(dim.width + toDim(dir).width, dim.height + toDim(dir).height);
    }
}
