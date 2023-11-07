package fr.uga.miashs.inff3.bataillenavale.startScreen;

import javax.swing.*;
import java.awt.*;

public class HowToPlayScreen extends JFrame{

    private JPanel mainPanel;
    private JButton returnButton;

    public HowToPlayScreen() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
    }

    public JButton getReturnButton(){
        return returnButton;
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
