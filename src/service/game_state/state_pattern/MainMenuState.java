package service.game_state.state_pattern;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import service.game_state.IGameState;

import java.awt.*;

/**
 * Represents the main menu state, displaying the game title and start options.
 */
public class MainMenuState implements IGameState {
    /**
     * Updates the main menu state. Currently, no updates are performed.
     *
     * @param context the game context containing game data
     */
    @Override
    public void update(GameContext context) {}

    /**
     * Renders the main menu screen.
     *
     * @param g2 the graphics context for rendering
     * @param renderer the game renderer for drawing the main menu
     * @param context the game context containing game data
     */
    @Override
    public void render(Graphics2D g2, GameRenderer renderer, GameContext context) {
        System.out.println("MainMenuStare");
        renderer.drawMainMenu(g2, context.getWidth(), context.getHeight(), context);
    }
}