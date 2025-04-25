package core.game_loop.panel;

import core.game_loop.GameContext;
import core.game_loop.GameLoop;
import core.game_loop.GameRenderer;
import core.game_loop.GameRestarter;
import data.SaveManager;
import input.Key;
import service.keyboard.KeyboardHandlerService;
import service.game_state.GameStateManager;
import service.keyboard.MouseHandlerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Game panel responsible for rendering and handling game logic.
 */

public class PanelGame extends JComponent {
    /** Main rendering surface */
    private Graphics2D g2;

    /** Buffered image used for off-screen rendering */
    private BufferedImage image;

    /** Screen dimensions */
    private int width;
    private int height;

    /** Flag to control game loop */
    private boolean start = true;

    /** Keyboard main.input handler */
    private final Key key = new Key();
    private final GameStateManager gameStateManager = new GameStateManager();

    private GameLoop gameLoop;
    private GameContext context;
    private GameRenderer renderer;

    private final GameRestarter restarter = new GameRestarter();

    private final SaveManager saveManager = new SaveManager();

    private final MouseHandlerService mouseHandler = new MouseHandlerService();

    /**
     * Initializes the game, rendering engine, user main.input and starts the game loop.
     */
    public void start() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.ALL);
        for (var handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.ALL);
        }

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setFocusable(true);
        requestFocus();

        addKeyboardControl();

        width = getWidth() > 0 ? getWidth() : 1366;
        height = getHeight() > 0 ? getHeight() : 768;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        PanelInitializer initializer = new PanelInitializer();
        context = initializer.createContext(key, gameStateManager, width, height);
        renderer = initializer.createRenderer();
        gameLoop = initializer.createGameLoop(this, context, renderer);

        gameLoop.start();

        repaint();
    }

    /**
     * Sets up keyboard main.input handling and player control logic in a loop.
     * Handles movement, rotation, and weapon switching.
     */
    private void addKeyboardControl() {
        KeyboardHandlerService keyboardHandler = new KeyboardHandlerService(saveManager, restarter);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyboardHandler.handleKeyPressed(e, context, renderer, width);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyboardHandler.handleKeyReleased(e, key);
            }
        });
    }

    /**
     * Handles mouse click detection for manual screen navigation.
     * Listens for clicks on "How to Play" and "Back" buttons.
     *
     * @param e the mouse event to process
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        mouseHandler.handleClick(e, context, renderer);
        super.processMouseEvent(e);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("paint");
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }

    /**
     * Gets the Graphics2D context for rendering.
     *
     * @return the Graphics2D rendering context
     */
    public Graphics2D getG2() {
        return g2;
    }

    /**
     * Gets the width of the game panel
     *
     * @return the width in pixels
     */
    public int getWidthValue() {
        return width;
    }

    /**
     * Gets the height of the game panel.
     *
     * @return the height in pixels
     */
    public int getHeightValue() {
        return height;
    }

    /**
     * Checks if the game loop is running
     *
     * @return true if the game is running, false otherwise
     */
    public boolean isStart() {
        return start;
    }

    /**
     * Gets the buffered image used for off-screen rendering.
     *
     * @return the buffered image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Gets the game context for high score saving.
     *
     * @return the current GameContext
     */
    public GameContext getContext() {
        return context;
    }
}