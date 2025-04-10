package controller;

import logic_units.Collision;
import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.GameObject;
import model.Player;
import model.enemies.FastEnemy;
import model.enemies.TankEnemy;
import model.weapon.factory.FlameFactory;
import model.weapon.factory.LaserFactory;
import model.weapon.factory.RocketFactory;
import model.weapon.factory.WeaponFactory;
import model.weapon.AbstractWeapon;
import model.weapon.Laser;
import model.weapon.Weapon;
import service.game_loop.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    /** Player object */
    private Player player;

    /** List of all enemies on screen */
    private List<AbstractEnemy> enemies;

    /** List of all active weapons on screen */
    private List<AbstractWeapon> weapons;

    /** Factory for generating weapon instances */
    private WeaponFactory weaponFactory = new RocketFactory();

    /** Game Loop */
    private GameLoop gameLoop;

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

        player = new Player();
        player.changeLocation(width / 2.0, height / 2.0);
        player.setKey(key);

        enemies = new CopyOnWriteArrayList<>();
        weapons = new CopyOnWriteArrayList<>();

        gameLoop = new GameLoop(this, player, enemies, weapons, key, weaponFactory);
        gameLoop.start();
    }

    /**
     * Resets game state after Game Over or restart.
     * Clears enemies and weapons, resets score and player position.
     */
    private void restart() {
        enemies.clear();
        weapons.clear();
        score = 0;
        gameOver = false;

        player.changeLocation(width / 2.0, height / 2.0);
        player.setAngle(0);
        player.setHp(100);
    }

    /**
     * Creates and initializes the player object and background wave spawning thread.
     */
    private void initObjects() {
        player = new Player();
        player.changeLocation(150, 150);
        new Thread(() -> {
            while (start) {
                if (enemies.isEmpty()) {
                    spawnEnemyWave();
                }
                sleep(1000);
            }
        }).start();
    }

    /**
     * Spawns a new wave of enemies based on score and wave number.
     * Adjusts difficulty dynamically by increasing speed and count.
     */
    private void spawnEnemyWave() {
        waveInProgress = true;
        waveShowText = true;
        waveStartTime = System.currentTimeMillis();
        waveNumber = 1 + score / 10;

        int enemiesToSpawn = 3 + waveNumber * 2;
        float baseSpeed = 0.3f + waveNumber * 0.1f;

        Random random = new Random();
        for (int i = 0; i < enemiesToSpawn; i++) {
            int y = random.nextInt(height - 50) + 25;
            boolean fromLeft = i % 2 == 0;
            int x = fromLeft ? 0 : width;
            float angle = fromLeft ? 0f : 180f;

            AbstractEnemy enemy;
            int type = random.nextInt(100);
            if (type < 60) {
                enemy = new BasicEnemy(x, y);
            } else if (type < 85) {
                enemy = new FastEnemy(x, y);
            } else {
                enemy = new TankEnemy(x, y);
            }

            enemy.setAngle(angle);
            enemy.setSpeed(baseSpeed);
            enemies.add(enemy);
        }
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
                    case KeyEvent.VK_A -> key.setKey_left(true);
                    case KeyEvent.VK_D -> key.setKey_right(true);
                    case KeyEvent.VK_SPACE -> key.setKey_space(true);
                    case KeyEvent.VK_J -> {
                        key.setKey_j(true);
                        weaponFactory = new RocketFactory();
                    }
                    case KeyEvent.VK_K -> {
                        key.setKey_k(true);
                        weaponFactory = new FlameFactory();
                    }
                    case KeyEvent.VK_L -> {
                        key.setKey_l(true);
                        weaponFactory = new LaserFactory();
                    }
                    case KeyEvent.VK_ESCAPE -> System.exit(0);
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
        if (e.getID() == MouseEvent.MOUSE_CLICKED) {
            Point p = e.getPoint();

            if (inMainMenu && manualButton.contains(p)) {
                showingManual = true;
            }

            if (showingManual && manualBackButton.contains(p)) {
                showingManual = false;
            }
        }
        super.processMouseEvent(e);
    }

    /**
     * Creates and runs a thread for managing weapon movement and collisions.
     */
    private void initWeaponThread() {
        new Thread(() -> {
            while (start) {
                weapons.removeIf(w -> !w.check(width, height));
                for (Weapon w : weapons) {
                    ((GameObject) w).update();
                }
                checkWeaponHits();
                sleep(10);
            }
        }).start();
    }

    /**
     * Checks collisions between weapons and enemies.
     * Applies damage and removes defeated enemies and used weapons.
     */
    private void checkWeaponHits() {
        List<Weapon> toRemoveWeapons = new ArrayList<>();
        List<AbstractEnemy> toRemoveEnemies = new ArrayList<>();

        for (Weapon weapon : weapons) {
            for (AbstractEnemy enemy : enemies) {
                if (Collision.intersects((GameObject) weapon, enemy)) {
                    if (enemy instanceof BasicEnemy basicEnemy) {
                        ((AbstractWeapon) weapon).hit(basicEnemy);
                    }
                    if (enemy.isDead()) {
                        toRemoveEnemies.add(enemy);
                        score++;
                    }
                    if (!(weapon instanceof Laser)) {
                        toRemoveWeapons.add(weapon);
                    }
                    break;
                }
            }
        }

        weapons.removeAll(toRemoveWeapons);
        enemies.removeAll(toRemoveEnemies);
    }

    /**
     * Fills background with a solid color to clear the screen.
     */
    private void drawBackground() {
        g2.setColor(new Color(30, 30, 30));
        g2.fillRect(0, 0, width, height);
    }

    /**
     * Core drawing logic for the game.
     * Renders player, enemies, projectiles, HUD, and handles screen transitions.
     */
    private void drawGame() {
        if (waveShowText) {
            long elapsed = System.currentTimeMillis() - waveStartTime;
            if (elapsed < 2000) {
                String waveMsg = "WAVE " + (waveNumber - 1);
                g2.setColor(Color.YELLOW);
                g2.setFont(new Font("Arial", Font.BOLD, 48));
                FontMetrics fm = g2.getFontMetrics();
                int x = (width - fm.stringWidth(waveMsg)) / 2;
                int y = height / 2;
                g2.drawString(waveMsg, x, y);
            } else {
                waveShowText = false;
                waveInProgress = false;
            }
        }

        if (showingManual) {
            drawManual();
            return;
        }

        if (inMainMenu) {
            drawMainMenu();
            return;
        }

        if (gameOver) {
            drawGameOver();
            return;
        }

        player.draw(g2);
        for (Weapon weapon : weapons) {
            ((GameObject) weapon).draw(g2);
        }
        for (AbstractEnemy enemy : enemies) {
            enemy.draw(g2);
            if (Collision.intersects(player, enemy)) {
                player.takeDamage(50);
                enemies.remove(enemy);
                break;
            }
            if (player.isDead()) {
                gameOver = true;
            }
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("Score: " + score, 20, 30);

        int maxHP = 100;
        int hpBarWidth = 200;
        int hpBarHeight = 16;
        int padding = 20;

        int hp = player.getHp();
        int currentWidth = (int)(hpBarWidth * (hp / (float)maxHP));

        int barY = 30 - hpBarHeight / 2;
        int barX = width - hpBarWidth - padding;

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g2.getFontMetrics();
        int hpLabelWidth = fm.stringWidth("HP:");
        g2.drawString("HP:", barX - hpLabelWidth - 10, barY + hpBarHeight - 2);

        g2.setColor(new Color(60, 60, 60));
        g2.fillRoundRect(barX, barY, hpBarWidth, hpBarHeight, 10, 10);

        Color barColor;
        float hpPercent = hp / (float) maxHP;

        if (hpPercent >= 0.7f) {
            barColor = new Color(0, 200, 0);
        } else if (hpPercent >= 0.3f) {
            barColor = new Color(255, 200, 0);
        } else {
            barColor = new Color(200, 0, 0);
        }
        g2.setColor(barColor);

        g2.fillRoundRect(barX, barY, currentWidth, hpBarHeight, 10, 10);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(barX, barY, hpBarWidth, hpBarHeight, 10, 10);
    }

    /**
     * Renders the Game Over screen with score and restart/exit hints.
     */
    private void drawGameOver() {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 80));
        String message = "Game Over";
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(message)) / 2;
        int y = (height - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(message, x, y);

        String scoreMsg = "Score: " + score;
        g2.setColor(new Color(255, 215, 0));
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        FontMetrics scoreFM = g2.getFontMetrics();
        int scoreX = (width - scoreFM.stringWidth(scoreMsg)) / 2;
        int scoreY = y + 100;
        g2.drawString(scoreMsg, scoreX, scoreY);

        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String hint = "Please ESC to exit" + " | Press R to restart";
        int hintX = (width - g2.getFontMetrics().stringWidth(hint)) / 2;
        int hintY = height - 50;
        g2.drawString(hint, hintX, hintY);
    }

    /**
     * Renders the main menu screen with game title and start/manual options.
     */
    private void drawMainMenu() {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.CYAN);
        g2.setFont(new Font("Arial", Font.BOLD, 64));
        String title = "Sky Defender";
        FontMetrics titleFM = g2.getFontMetrics();
        int titleX = (width - titleFM.stringWidth(title)) / 2;
        int titleY = height / 2 - 100;
        g2.drawString(title, titleX, titleY);

        g2.setColor(new Color(255, 215, 0));
        g2.setFont(new Font("Arial", Font.BOLD, 32));
        String startMsg = "Press ENTER to start";
        FontMetrics msgFM = g2.getFontMetrics();
        int msgX = (width - msgFM.stringWidth(startMsg)) / 2;
        int msgY = titleY + 100;
        g2.drawString(startMsg, msgX, msgY);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String escHint = "Press ESC to quit";
        int escX = (width - g2.getFontMetrics().stringWidth(escHint)) / 2;
        int escY = height - 50;
        g2.drawString(escHint, escX, escY);

        String buttonText = "HOW TO PLAY";
        g2.setFont(new Font("Arial", Font.BOLD, 28));
        FontMetrics fmBtn = g2.getFontMetrics();
        int btnWidth = fmBtn.stringWidth(buttonText) + 40;
        int btnHeight = 40;
        int btnX = (width - btnWidth) / 2;
        int btnY = height / 2 + 50;

        manualButton.setBounds(btnX, btnY, btnWidth, btnHeight);

        g2.setColor(new Color(70, 70, 70));
        g2.fillRoundRect(btnX, btnY, btnWidth, btnHeight, 15, 15);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(btnX, btnY, btnWidth, btnHeight, 15, 15);
        g2.drawString(buttonText, btnX + 20, btnY + 28);
    }

    /**
     * Renders the How To Play manual screen with control instructions.
     */
    private void drawManual() {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        String title = "HOW TO PLAY";
        FontMetrics titleFM = g2.getFontMetrics();
        int titleX = (width - titleFM.stringWidth(title)) / 2;
        g2.drawString(title, titleX, 100);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String[] lines = {
                "- A / D: Rotate plane",
                "- SPACE: Accelerate",
                "- J: Rocket  |  K: Flame  |  L: Laser",
                "- Destroy enemies to gain score",
                "- Survive waves that increase in difficulty"
        };
        int y = 160;
        for (String line : lines) {
            g2.drawString(line, 60, y);
            y += 30;
        }

        String backText = "BACK";
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fmBack = g2.getFontMetrics();
        int backWidth = fmBack.stringWidth(backText) + 40;
        int backHeight = 40;
        int backX = (width - backWidth) / 2;
        int backY = height - 80;

        manualBackButton.setBounds(backX, backY, backWidth, backHeight);

        g2.setColor(new Color(60, 60, 60));
        g2.fillRoundRect(backX, backY, backWidth, backHeight, 10, 10);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(backX, backY, backWidth, backHeight, 10, 10);
        g2.drawString(backText, backX + 20, backY + 28);
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