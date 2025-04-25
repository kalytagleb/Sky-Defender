package service.game_state;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;

import java.awt.*;

/**
 * Manages the current game state and delegates update and main.render calls.
 */
public class GameStateManager {
    /** The current game state */
    private IGameState currentState;

    /**
     * Sets the current game state.
     *
     * @param state the new game state
     */
    public void setCurrentState(IGameState state) {
        this.currentState = state;
    }

    /**
     * Gets the current game state.
     *
     * @return the current game state
     */
    public IGameState getCurrentState() {
        return currentState;
    }

    /**
     * Updates the current game state.
     *
     * @param context the game context containing game main.core.data
     */
    public void update(GameContext context) {
        currentState.update(context);
    }

    /**
     * Renders the current game state to the screen.
     *
     * @param g2 the graphics context for rendering
     * @param renderer the game renderer for drawing game elements
     * @param context the game context containing game main.core.data
     */
    public void render(Graphics2D g2, GameRenderer renderer, GameContext context) {
        currentState.render(g2, renderer, context);
    }
}