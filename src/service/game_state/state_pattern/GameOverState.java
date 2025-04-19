package service.game_state.state_pattern;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import service.game_state.IGameState;

import java.awt.*;

/**
 * Represents the game over state, displaying the final score.
 */
public class GameOverState implements IGameState {
    /**
     * Updates the game over state. Currently, no updates are performed.
     *
     * @param context the game context containing game data
     */
    @Override
    public void update(GameContext context) {}

    /**
     * Renders the game over screen with the final score.
     *
     * @param g2 the graphics context for rendering
     * @param renderer the game renderer for drawing the game over screen
     * @param context the game context containing game data
     */
    @Override
    public void render(Graphics2D g2, GameRenderer renderer, GameContext context) {
        int score = context.getScore();
        renderer.drawGameOver(g2, context.getWidth(), context.getHeight(), score);
    }
}