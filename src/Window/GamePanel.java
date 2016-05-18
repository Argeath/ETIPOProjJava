package Window;

import Game.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    private World world;

    private boolean Hexagonal = true;

    public Field fields[][];

    GamePanel(World w) {
        super(null);
        world = w;
        world.setGamePanel(this);

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

        setupKeys();
    }

    private void setupKeys() {
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftKey");
        getActionMap().put("LeftKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.update(KeyEvent.VK_LEFT);
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightKey");
        getActionMap().put("RightKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.update(KeyEvent.VK_RIGHT);
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UpKey");
        getActionMap().put("UpKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.update(KeyEvent.VK_UP);
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DownKey");
        getActionMap().put("DownKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.update(KeyEvent.VK_DOWN);
            }
        });
    }
}
