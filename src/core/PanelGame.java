package core;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import core.game_loop.GameRestarter;
import data.SaveManager;
import input.Key;
import model.enemies.AbstractEnemy;
import model.Player;
import factory.weapon.FlameFactory;
import factory.weapon.LaserFactory;
import factory.weapon.RocketFactory;
import model.weapon.AbstractWeapon;
import core.game_loop.GameLoop;
import service.game_state.GameState;
import service.game_state.GameStateManager;
import service.waves.WaveManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

        Player player = new Player();
        player.changeLocation(width / 2.0, height / 2.0);
        player.setKey(key);

        List<AbstractEnemy> enemies = new CopyOnWriteArrayList<>();
        List<AbstractWeapon> weapons = new CopyOnWriteArrayList<>();
        WaveManager waveManager = new WaveManager(enemies, width, height);

        context = new GameContext(player, enemies, weapons, key, waveManager, gameStateManager);
        renderer = new GameRenderer();
        gameLoop = new GameLoop(this, context, renderer);
        gameLoop.start();
    }

    /**
     * Sets up keyboard input handling and player control logic in a loop.
     * Handles movement, rotation, and weapon switching.
     */
    private void addKeyboardControl() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE -> System.exit(0);
                    case KeyEvent.VK_ENTER -> {
                        if (gameStateManager.is(GameState.MAIN_MENU)) {
                            gameStateManager.setState(GameState.PLAYING);
                            restarter.restart(context, width, height);
                        }
                    }
                    case KeyEvent.VK_R -> {
                        if (gameStateManager.is(GameState.GAME_OVER)) {
                            gameStateManager.setState(GameState.PLAYING);
                            restarter.restart(context, width, height);
                        }
                    }
                    case KeyEvent.VK_M -> gameStateManager.setState(GameState.MANUAL);
                    case KeyEvent.VK_J -> {
                        context.setWeaponFactory(new RocketFactory());
                    }
                    case KeyEvent.VK_K -> {
                        context.setWeaponFactory(new FlameFactory());
                    }
                    case KeyEvent.VK_L -> {
                        context.setWeaponFactory(new LaserFactory());
                    }
                    case KeyEvent.VK_P -> {
                        if (gameStateManager.is(GameState.PLAYING)) {
                            gameStateManager.setState(GameState.PAUSED);
                        } else if (gameStateManager.is(GameState.PAUSED)) {
                            gameStateManager.setState(GameState.PLAYING);
                        }
                    }
                    case KeyEvent.VK_S -> saveManager.save(context, renderer, width);
                    case KeyEvent.VK_X -> saveManager.load(context, renderer, width);
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A -> key.setKey_left(true);
                    case KeyEvent.VK_D -> key.setKey_right(true);
                    case KeyEvent.VK_SPACE -> key.setKey_space(true);
                    case KeyEvent.VK_J -> key.setKey_j(true);
                    case KeyEvent.VK_K -> key.setKey_k(true);
                    case KeyEvent.VK_L -> key.setKey_l(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A -> key.setKey_left(false);
                    case KeyEvent.VK_D -> key.setKey_right(false);
                    case KeyEvent.VK_SPACE -> key.setKey_space(false);
                    case KeyEvent.VK_J -> key.setKey_j(false);
                    case KeyEvent.VK_K -> key.setKey_k(false);
                    case KeyEvent.VK_L -> key.setKey_l(false);
                }
            }
        });
    }

    /**
     * Handles mouse click detection for manual screen navigation.
     * Listens for clicks on "How to Play" and "Back" buttons.
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() != MouseEvent.MOUSE_CLICKED) return;

        Point p = e.getPoint();

        if (gameStateManager.is(GameState.MAIN_MENU) &&
                renderer.getManualButtonBounds().contains(p)) {
            gameStateManager.setState(GameState.MANUAL);
        }

        if (gameStateManager.is(GameState.MANUAL) &&
            renderer.getManualBackButtonBounds().contains(p)) {
            gameStateManager.setState(GameState.MAIN_MENU);
        }

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