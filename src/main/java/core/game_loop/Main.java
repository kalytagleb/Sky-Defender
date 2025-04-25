package core.game_loop;

import core.game_loop.panel.PanelGame;
import data.score.HighScoreManager;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Main class for our game, responsible for initializing the game window.
 */

public class Main extends JFrame {
    private PanelGame panelGame = new PanelGame();

    /**
     * Constructs the main game window and initializes its components
     */
    public Main() {
        init();
    }

    public PanelGame getPanelGame() {
        return panelGame;
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
        panelGame = new PanelGame();
        add(panelGame);
        revalidate();
        repaint();
        // method, which allows to intercept window events
        addWindowListener(new WindowAdapter() {
            /**
             * Called when the window is closing.
             * Used to save the best score.
             *
             * @param e the event triggered when the window is closing
             */
            @Override
            public void windowClosing(WindowEvent e) {
                GameContext context = panelGame.getContext();
                if (context != null) {
                    HighScoreManager.save(context.getHighScore());
                }
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
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
            main.getPanelGame().start();
        });
    }
}