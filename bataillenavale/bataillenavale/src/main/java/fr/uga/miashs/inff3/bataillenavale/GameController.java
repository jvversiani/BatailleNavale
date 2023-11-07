package fr.uga.miashs.inff3.bataillenavale;

import fr.uga.miashs.inff3.bataillenavale.gameScreen.BatailleNavale;
import fr.uga.miashs.inff3.bataillenavale.startScreen.HowToPlayScreen;
import fr.uga.miashs.inff3.bataillenavale.startScreen.StartScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GameController {
    private static JPanel cardPanel;
    private static CardLayout cardLayout;
    private static StartScreen startScreen;
    private JPanel panelBatailleNavale;
    private BatailleNavale batailleNavale;

    public GameController() throws InterruptedException {
        JFrame mainFrame = new JFrame("Bataille Navale");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 600);
        mainFrame.setResizable(false);

        startScreen = new StartScreen();
        cardPanel.add(startScreen.getMainPanel(), "startScreen");
        HowToPlayScreen howToPlay = new HowToPlayScreen();
        cardPanel.add(howToPlay.getMainPanel(), "howToPlayScreen");
        batailleNavale = new BatailleNavale();

        startScreen.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBatailleNavale = BatailleNavale.initFenetre(10);
                cardPanel.add(panelBatailleNavale, "gameScreen");
                cardLayout.show(cardPanel, "gameScreen");
                batailleNavale = new BatailleNavale();

                Thread gameThread = new Thread(() -> {
                    try {
                        batailleNavale.start();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                gameThread.start();
            }
        });

        startScreen.getHowToPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "howToPlayScreen");
            }
        });

        howToPlay.getReturnButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "startScreen");
            }
        });

        startScreen.getAboutTheCreatorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebPage("https://www.linkedin.com/in/joao-victor-gouvea-2b8872207/");
            }
        });

        mainFrame.add(cardPanel);
        cardLayout.show(cardPanel, "startScreen");
        mainFrame.setVisible(true);
    }

    public static JPanel getCardPanel(){
        return cardPanel;
    }

    public static CardLayout  getCardLayout(){
        return cardLayout;
    }

    public static JButton getPlayButton(){
        return startScreen.getPlayButton();
    }

    public void openWebPage(String url){
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GameController();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

