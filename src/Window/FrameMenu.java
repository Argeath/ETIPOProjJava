package Window;

import Game.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

class FrameMenu extends JMenuBar {
    private final JFileChooser fc = new JFileChooser();

    private World world;

    FrameMenu(World w) {
        world = w;

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);

        JMenuItem item;

        item = new JMenuItem("New", KeyEvent.VK_N);
        item.addActionListener(e -> {
            boolean gotInt = false;
            int width, height;
            width = height = 0;

            do {
                String num = JOptionPane.showInputDialog(
                        FrameMenu.this,
                        "Podaj szerokosc mapy: (max 40)",
                        "Szerokosc mapy");

                try {
                    if(num == null) return;
                    width = Integer.parseInt(num);
                    if(width <= 40)
                        gotInt = true;
                } catch (NumberFormatException | NullPointerException ex) {
                    gotInt = false;
                }
            } while(!gotInt);

            do {
                String num = JOptionPane.showInputDialog(
                        FrameMenu.this,
                        "Podaj wysokosc mapy: (max 20)",
                        "Wysokosc mapy");

                try {
                    if(num == null) return;
                    height = Integer.parseInt(num);
                    if(height <= 20)
                        gotInt = true;
                } catch (NumberFormatException | NullPointerException ex) {
                    gotInt = false;
                }
            } while(!gotInt);

            MainFrame.mainFrame.setVisible(false);
            MainFrame.mainFrame.dispose();

            MainFrame.mainFrame = new MainFrame(new Dimension(width, height));
        });
        fileMenu.add(item);

        fileMenu.addSeparator();

        item = new JMenuItem("Open...", KeyEvent.VK_O);
        item.addActionListener(e -> {
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    World result = (World) ois.readObject();

                    world = result;

                    MainFrame.mainFrame.setVisible(false);
                    MainFrame.mainFrame.dispose();

                    MainFrame.mainFrame = new MainFrame(world);

                    ois.close();
                } catch(IOException | ClassNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
        fileMenu.add(item);

        item = new JMenuItem("Save as...", KeyEvent.VK_S);
        item.addActionListener(e -> {
            int returnVal = fc.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

                world.save(file);
            }
        });
        fileMenu.add(item);

        item = new JMenuItem("Cast spell", KeyEvent.VK_SPACE);
        item.addActionListener(e -> world.getHuman().castSpell());
        add(item);
    }
}
