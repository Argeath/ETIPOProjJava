package Window;

import Game.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class FrameMenu extends JMenuBar {

    private World world;

    FrameMenu(World w) {
        world = w;

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);

        JMenuItem item;

        item = new JMenuItem("New", KeyEvent.VK_N);
        fileMenu.add(item);

        item = new JMenuItem("Open...", KeyEvent.VK_O);
        fileMenu.add(item);

        item = new JMenuItem("Save as...", KeyEvent.VK_S);
        fileMenu.add(item);

        fileMenu.addSeparator();

        item = new JMenuItem("Quit", KeyEvent.VK_Q);
        fileMenu.add(item);

        item = new JMenuItem("Cast spell", KeyEvent.VK_SPACE);
        item.addActionListener(e -> {
           world.getHuman().castSpell();
        });
        add(item);
    }
}
