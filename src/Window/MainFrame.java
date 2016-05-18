package Window;

import Game.Organisms.Organism;
import Game.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MainFrame extends JFrame {

    static HashMap<Organism.OrganismType, Image> images = new HashMap<>();

    public static MainFrame mainFrame;

    public static JTextArea logsArea;

    public static void main(String[] args) {
        mainFrame = new MainFrame(new Dimension(20, 20));
    }

    MainFrame(Dimension mapSize) {
        World world = new World(mapSize);
        world.init();

        setup(world, mapSize);
    }

    MainFrame(World w) {
        setup(w, w.getSize());
    }

    private void setup(World world, Dimension mapSize) {
        setLayout(new BorderLayout());
        setTitle("Symulator Świata - Dominik Kinal gr 6 nr 160589");

        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setJMenuBar(new FrameMenu(world));

        JPanel gamePanel = new GamePanel(world);
        add(gamePanel, "Center");

        logsArea = new JTextArea();
        logsArea.setEditable(false);
        logsArea.setLineWrap(true);
        logsArea.setWrapStyleWord(true);

        JScrollPane logsPanel = new JScrollPane(logsArea);
        logsPanel.setBackground(Color.white);
        logsPanel.setPreferredSize(new Dimension(mapSize.width * 32, 100));
        add(logsPanel, "South");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        for(Organism.OrganismType type : Organism.OrganismType.values()) {
            try {
                images.put(type, ImageIO.read(new File("images/" + type.toString().toLowerCase() + ".png")));
            } catch (IOException ignored) {}
        }
    }

}
