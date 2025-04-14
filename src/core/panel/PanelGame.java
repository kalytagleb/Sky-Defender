package core.panel;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import core.game_loop.GameRestarter;
import data.SaveManager;
import input.Key;
import core.game_loop.GameLoop;
import service.keyboard.KeyboardHandlerService;
import service.game_state.GameStateManager;
import service.keyboard.MouseHandlerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * PanelGame is the core component responsible for running the game loop,
 * rendering graphics, handling user input, managing game state transitions,
 * and orchestrating game objects such as the player, enemies, and weapons.
 *
 * <p>This panel supports multiple game states:
 * <ul>
 *     <li>Main menu</li>
 *     <li>In-game play</li>
 *     <li>How-to-play manual</li>
 *     <li>Game over screen</li>
 * </ul>
 *
 * <p>Main responsibilities include:
 * <ul>
 *     <li>Initializing the game objects and UI</li>
 *     <li>Rendering all graphics to a buffered image</li>
 *     <li>Spawning enemy waves based on the player's score</li>
 *     <li>Detecting collisions and applying weapon effects</li>
 *     <li>Displaying health bar and score</li>
 *     <li>Listening to keyboard and mouse events</li>
 * </ul>
 *
 * <p>This class uses OOP principles like encapsulation, polymorphism,
 * and the strategy and factory patterns for flexibility and modularity.
 *
 * @author [Gleb Kalyta]
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

    /** Keyboard input handler */
    private final Key key = new Key();
    private final GameStateManager gameStateManager = new GameStateManager();

    private GameLoop gameLoop;
    private GameContext context;
    private GameRenderer renderer;

    private final GameRestarter restarter = new GameRestarter();

    private final SaveManager saveManager = new SaveManager();

    private final MouseHandlerService mouseHandler = new MouseHandlerService();

    /**
     * Initializes the game, rendering engine, user input and starts the game loop.
     */
    public void start() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setFocusable(true);
        requestFocus();
        addKeyboardControl();

        width = getWidth();
        height = getHeight();

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        PanelInitializer initializer = new PanelInitializer();

        context = initializer.createContext(key, gameStateManager, width, height);
        renderer = initializer.createRenderer();
        gameLoop = initializer.createGameLoop(this, context, renderer);
        gameLoop.start();
    }

    /**
     * Sets up keyboard input handling and player control logic in a loop.
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
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        mouseHandler.handleClick(e, context, renderer);
        super.processMouseEvent(e);
    }

    public Graphics2D getG2() {
        return g2;
    }

    public int getWidthValue() {
        return width;
    }

    public int getHeightValue() {
        return height;
    }

    public boolean isStart() {
        return start;
    }

    public BufferedImage getImage() {
        return image;
    }
}