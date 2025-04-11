package core.game_loop;

import org.w3c.dom.css.Rect;
import render.game_scene.HudRenderer;
import render.game_scene.WaveTextRenderer;
import render.screen.GameOverRenderer;
import render.screen.MainMenuRenderer;
import render.screen.ManualRenderer;
import service.game_state.GameState;
import service.game_state.GameStateManager;

import java.awt.*;

public class GameRenderer {

    private final WaveTextRenderer waveTextRenderer = new WaveTextRenderer();
    private final HudRenderer hudRenderer = new HudRenderer();

    private final MainMenuRenderer mainMenuRenderer = new MainMenuRenderer();
    private final ManualRenderer manualRenderer = new ManualRenderer();
    private final GameOverRenderer gameOverRenderer = new GameOverRenderer();

    private final Rectangle manualButton = new Rectangle();
    private final Rectangle manualBackButton = new Rectangle();

    public void draw(Graphics2D g2, GameContext context, int width, int height) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        GameStateManager gsm = context.getGameStateManager();

        if (gsm.is(GameState.MANUAL)) {
            manualRenderer.draw(g2, width, height, manualBackButton);
            return;
        }

        if (gsm.is(GameState.MAIN_MENU)) {
            mainMenuRenderer.draw(g2, width, height, manualButton);
            return;
        }

        if (gsm.is(GameState.GAME_OVER)) {
            gameOverRenderer.draw(g2, width, height, context.getScore());
            return;
        }

        context.getPlayer().draw(g2);
        context.getWeapons().forEach(w -> w.draw(g2));
        context.getEnemies().forEach(e -> e.draw(g2));

        waveTextRenderer.draw(g2, context.getWaveManager(), width, height);
        hudRenderer.draw(g2, context);
    }

    public Rectangle getManualButtonBounds() {
        return manualButton;
    }

    public Rectangle getManualBackButtonBounds() {
        return manualBackButton;
    }
}