package Window;

import javax.swing.*;
import java.awt.event.KeyEvent;

class FrameMenu extends JMenuBar {

    FrameMenu() {
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

        item = new JMenuItem("Next Round", KeyEvent.VK_SPACE);
        add(item);
    }
}
