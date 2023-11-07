package fr.uga.miashs.inff3.bataillenavale.startScreen;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame{
    private JButton aboutTheCreatorButton;
    private JButton playButton;
    private JButton howToPlayButton;
    private JPanel mainPanel;

    public StartScreen() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public JButton getPlayButton(){
        return playButton;
    }

    public JButton getHowToPlayButton(){
        return howToPlayButton;
    }

    public JButton getAboutTheCreatorButton(){
        return aboutTheCreatorButton;
    }
}
