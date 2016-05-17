package Window;

import Game.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    private World world;

    private boolean Hexagonal = false;

    public Field fields[][];

    GamePanel() {
        super(null);

        world = new World(this);
        world.init();

        if(Hexagonal)
            setPreferredSize(new Dimension(world.getSize().width * 50 + 16, world.getSize().height * 17 + 19));
        else
            setPreferredSize(new Dimension(world.getSize().width * 33 + 2, world.getSize().height * 33 + 2));

        setBackground(Color.lightGray);
        setBorder(BorderFactory.createLineBorder(Color.black));

        fields = new Field[world.getSize().height][world.getSize().width];

        Insets insets = getInsets();
        Dimension size = new Dimension(32, 32);

        for(int iy = 0; iy < world.getSize().height; iy++)
            for(int ix = 0; ix < world.getSize().width; ix++) {
                fields[iy][ix] = new Field(Hexagonal, ix, iy, world);

                if(Hexagonal)
                    fields[iy][ix].setBounds(ix * 50 + (iy % 2 * 25) + insets.left, iy * 17 + insets.top, size.width, size.height);
                else
                    fields[iy][ix].setBounds(ix * 33 + insets.left, iy * 33 + insets.top, size.width, size.height);

                add(fields[iy][ix]);
            }

        world.render();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
