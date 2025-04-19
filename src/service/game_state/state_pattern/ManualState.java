package service.game_state.state_pattern;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import service.game_state.IGameState;

import java.awt.*;

/**
 * Represents the manual state, displaying game instructions.
 */
public class ManualState implements IGameState {
    /**
     * Updates the manual state. Currently, no updates are performed.
     *
     * @param context the game context containing game data
     */
    @Override
    public void update(GameContext context) {}

    /**
     * Renders the manual screen with game instructions.
     *
     * @param g2 the graphics context for rendering
     * @param renderer the game renderer for drawing the manual screen
     * @param context the game context containing game data
     */
    @Override
    public void render(Graphics2D g2, GameRenderer renderer, GameContext context) {
        renderer.drawManual(g2, context.getWidth(), context.getHeight(), renderer.getManualBackButtonBounds());
    }
}