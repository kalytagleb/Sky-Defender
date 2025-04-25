package core.game_loop;

import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import render.game_scene.HudRenderer;
import render.game_scene.PauseRenderer;
import render.game_scene.WaveTextRenderer;
import render.screen.GameOverRenderer;
import render.screen.MainMenuRenderer;
import render.screen.ManualRenderer;
import render.screen.NotificationRenderer;

import java.awt.*;

/**
 * Renders the game state, including the main menu, game, pause screen, and game over screen.
 */
public class GameRenderer {

    private final WaveTextRenderer waveTextRenderer = new WaveTextRenderer();
    private final HudRenderer hudRenderer = new HudRenderer();

    private final MainMenuRenderer mainMenuRenderer = new MainMenuRenderer();
    private final ManualRenderer manualRenderer = new ManualRenderer();
    private final GameOverRenderer gameOverRenderer = new GameOverRenderer();
    private final PauseRenderer pauseRenderer = new PauseRenderer();
    private final NotificationRenderer notificationRenderer = new NotificationRenderer();

    private final Rectangle manualButton = new Rectangle();
    private final Rectangle manualBackButton = new Rectangle();

    /**
     * Draws the current game state to the graphics context.
     *
     * @param g2 the graphics context.
     * @param context the game context
     * @param width the screen width
     * @param height the screen height
     */
    public void draw(Graphics2D g2, GameContext context, int width, int height) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        context.getGameStateManager().render(g2, this, context);
    }

    /**
     * Gets the bounds of the "How to Play" button.
     *
     * @return the rectangle representing the button bounds
     */
    public Rectangle getManualButtonBounds() {
        return manualButton;
    }

    /**
     * Gets the bounds of the "Back" button in the manual screen.
     *
     * @return the rectangle representing the button bounds.
     */
    public Rectangle getManualBackButtonBounds() {
        return manualBackButton;
    }

    /**
     * Displays a notification message on the screen.
     *
     * @param message the message to display
     */
    public void showNotification(String message) {
        notificationRenderer.showMessage(message);
    }

    /**
     * Draws the main menu screen.
     *
     * @param g2 the graphics context
     * @param width the screen width
     * @param height the screen height
     * @param context the game context
     */
    public void drawMainMenu(Graphics2D g2, int width, int height, GameContext context) {
        System.out.println("GameRenderer");
        mainMenuRenderer.draw(g2, width, height, manualButton, context);
    }

    /**
     * Draws the game state, including player, enemies, and weapons.
     *
     * @param g2 the graphics context
     * @param context the game context
     * @param width the screen width
     * @param height the screen height
     */
    public void drawGame(Graphics2D g2, GameContext context, int width, int height) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        context.getPlayer().draw(g2);

        for (AbstractWeapon weapon : context.getWeapons()) {
            weapon.draw(g2);
        }

        for (AbstractEnemy enemy : context.getEnemies()) {
            enemy.draw(g2);
        }

        waveTextRenderer.draw(g2, context.getWaveManager(), width, height);
        hudRenderer.draw(g2, context);
        notificationRenderer.draw(g2, width);
    }

    /**
     * Draws the paused game screen.
     *
     * @param g2 the graphics context
     * @param width the screen width
     * @param height the screen height
     */
    public void drawPaused(Graphics2D g2, int width, int height) {
        pauseRenderer.draw(g2, width, height);
    }

    /**
     * Draws the manual (how to play) screen.
     *
     * @param g2 the graphics context
     * @param width the screen width
     * @param height the screen height
     * @param manualBackButton the bounds of the back button
     */
    public void drawManual(Graphics2D g2, int width, int height, Rectangle manualBackButton) {
        manualRenderer.draw(g2, width, height, manualBackButton);
    }

    /**
     * Draws the game over screen with the final score.
     *
     * @param g2 the graphics context.
     * @param width the screen width
     * @param height the screen height
     * @param score the final score
     */
    public void drawGameOver(Graphics2D g2, int width, int height, int score) {
        gameOverRenderer.draw(g2, width, height, score);
    }
}