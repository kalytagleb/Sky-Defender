package core;

import core.panel.PanelGame;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Main class for our game, responsible for initializing the game window.
 */

public class Main extends JFrame {
    /**
     * Constructs the main game window and initializes its components
     */
    public Main() {
        init();
    }

    /**
     * Initializes the game window with title, size and components.
     * Sets up the game panel and window event listeners.
     */
    public void init() {
        setTitle("Sky Defender");
        setSize(1366, 768);
        setLocationRelativeTo(null); // In order to our main screen was on the center
        setResizable(false); // User can't resize game screen while playing
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // JFrame calls it by default, it's just for readability
        PanelGame panelGame = new PanelGame();
        add(panelGame);
        // method, which allows to intercept window events
        addWindowListener(new WindowAdapter() {
            // it calls, when window is opened first time
            // it helps to
            @Override
            public void windowOpened(WindowEvent e) {
                panelGame.start();
            }
        });
    }

    /**
     * Main entry point for our game
     * Creates and displays the game window.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
    }
}