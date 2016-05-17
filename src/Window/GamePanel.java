package Window;

import Game.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    private World world;

    public Field fields[][];

    GamePanel() {
        super(null);

        world = new World();
        setPreferredSize(new Dimension(world.getSize().width * 32 + 19, world.getSize().height * 32 + 2));
        setBackground(Color.lightGray);
        setBorder(BorderFactory.createLineBorder(Color.black));

        fields = new Field[world.getSize().height][world.getSize().width];


        Insets insets = getInsets();
        Dimension size = new Dimension(32, 32);

        for(int iy = 0; iy < world.getSize().height; iy++)
            for(int ix = 0; ix < world.getSize().width; ix++) {
                fields[iy][ix] = new Field();
                fields[iy][ix].setBounds(ix * 32 + (iy % 2 * 16) + insets.left, iy * 32 + insets.top,
                        size.width, size.height);
                add(fields[iy][ix]);
            }

        repaint();
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
