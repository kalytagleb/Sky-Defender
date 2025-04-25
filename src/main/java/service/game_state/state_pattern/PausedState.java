package service.game_state.state_pattern;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import service.game_state.IGameState;

import java.awt.*;

/**
 * Represents the paused state, displaying a pause screen.
 */
public class PausedState implements IGameState {
    /**
     * Updates the paused state. Currently, no updates are performed.
     *
     * @param context the game context containing game main.core.data
     */
    @Override
    public void update(GameContext context) {}

    /**
     * Renders the paused screen.
     *
     * @param g2 the graphics context for rendering
     * @param renderer the game renderer for drawing the pause screen
     * @param context the game context containing game main.core.data
     */
    @Override
    public void render(Graphics2D g2, GameRenderer renderer, GameContext context) {
        renderer.drawPaused(g2, context.getWidth(), context.getHeight());
    }
}