package fr.uga.miashs.inff3.bataillenavale.gameScreen;

import fr.uga.miashs.inff3.bataillenavale.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGamePanel {
    private JLabel resultLabel;
    private JButton playAgainButton;
    private JButton backToMainMenuButton;
    private JPanel mainPanel;

    public EndGamePanel() {
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = GameController.getCardLayout();
                JPanel cardPanel = GameController.getCardPanel();
                cardLayout.show(cardPanel, "startScreen");
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton playButton = GameController.getPlayButton();
                playButton.doClick();
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public JButton getStatisticsButton(){
        return backToMainMenuButton;
    }

    public JButton getSeeEnemySFleetButton(){
        return playAgainButton;
    }
}
