package Window;

import Game.Organisms.Organism;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MainFrame extends JFrame {

    static HashMap<Organism.OrganismType, Image> images = new HashMap<>();

    public static void main(String[] args) {
        new MainFrame();
    }

    private MainFrame() {
        setLayout(new BorderLayout());
        setTitle("Symulator Świata - Dominik Kinal gr 6 nr 160589");

        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setJMenuBar(new FrameMenu());

        Dimension mapSize = new Dimension(20, 20);

        JPanel gamePanel = new GamePanel();
        add(gamePanel, "Center");

        JPanel logsPanel = new JPanel(new BorderLayout());
        logsPanel.setBackground(Color.green);
        logsPanel.setPreferredSize(new Dimension(mapSize.width * 32, 50));
        add(logsPanel, "South");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        for(Organism.OrganismType type : Organism.OrganismType.values()) {
            try {
                images.put(type, ImageIO.read(new File("images/" + type.toString().toLowerCase() + ".png")));
            } catch (IOException ignored) {
                //TODO: Error to logs
            }
        }
    }

}
