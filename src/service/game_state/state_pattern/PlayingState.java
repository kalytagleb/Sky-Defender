package service.game_state.state_pattern;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import core.game_loop.GameUpdater;
import service.game_state.IGameState;

import java.awt.*;

/**
 * Represents the playing state, handling game updates and rendering during active gameplay.
 */
public class PlayingState implements IGameState {
    /** The game updater responsible for updating game logic */
    private final GameUpdater updater = new GameUpdater();

    /**
     * Updates the game state, including player, enemies, and weapons.
     *
     * @param context the game context containing game data
     */
    @Override
    public void update(GameContext context) {
        updater.update(context, context.getWidth(), context.getHeight());
    }

    /**
     * Renders the active game state, including player, enemies, and HUD.
     *
     * @param g2 the graphics context for rendering
     * @param renderer the game renderer for drawing game elements
     * @param context the game context containing game data
     */
    @Override
    public void render(Graphics2D g2, GameRenderer renderer, GameContext context) {
        renderer.drawGame(g2, context, context.getWidth(), context.getHeight());
    }
}