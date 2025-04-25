package service.game_state;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;

import java.awt.*;

/**
 * Interface defining the behavior of game states.
 * Implementations handle updating and rendering of specific game states.
 */
public interface IGameState {
    /**
     * Updates the game state based on the current game context.
     *
     * @param context the game context containing game main.core.data
     */
    void update(GameContext context);

    /**
     * Renders the game state to the screen.
     *
     * @param g2 the graphics context for rendering
     * @param renderer the game renderer for drawing game elements
     * @param context the game context containing game main.core.data
     */
    void render(Graphics2D g2, GameRenderer renderer, GameContext context);
}