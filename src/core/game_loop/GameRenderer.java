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
import service.game_state.GameStateManager;

import java.awt.*;

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

    public void draw(Graphics2D g2, GameContext context, int width, int height) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        context.getGameStateManager().render(g2, this, context);
    }

    public Rectangle getManualButtonBounds() {
        return manualButton;
    }

    public Rectangle getManualBackButtonBounds() {
        return manualBackButton;
    }

    public void showNotification(String message) {
        notificationRenderer.showMessage(message);
    }

    public void drawMainMenu(Graphics2D g2, int width, int height) {
        mainMenuRenderer.draw(g2, width, height, manualButton);
    }

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

    public void drawPaused(Graphics2D g2, int width, int height) {
        pauseRenderer.draw(g2, width, height);
    }

    public void drawManual(Graphics2D g2, int width, int height, Rectangle manualBackButton) {
        manualRenderer.draw(g2, width, height, manualBackButton);
    }

    public void drawGameOver(Graphics2D g2, int width, int height, int score) {
        gameOverRenderer.draw(g2, width, height, score);
    }
}