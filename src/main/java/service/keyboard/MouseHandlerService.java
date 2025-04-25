package service.keyboard;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import service.game_state.GameStateManager;
import service.game_state.state_pattern.MainMenuState;
import service.game_state.state_pattern.ManualState;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Handles mouse click events for navigating between game states.
 */
public class MouseHandlerService {
    /**
     * Processes mouse click events to handle navigation between main menu and manual states.
     *
     * @param e the mouse event
     * @param context the game context containing game main.core.data
     * @param renderer the game renderer for accessing button bounds
     */
    public void handleClick(MouseEvent e, GameContext context, GameRenderer renderer) {
        if (e.getID() != MouseEvent.MOUSE_CLICKED) return;

        Point p = e.getPoint();
        GameStateManager gsm = context.getGameStateManager();

        if (gsm.getCurrentState() instanceof MainMenuState && renderer.getManualButtonBounds().contains(p)) {
            gsm.setCurrentState(new ManualState());
        }

        if (gsm.getCurrentState() instanceof ManualState && renderer.getManualBackButtonBounds().contains(p)) {
            gsm.setCurrentState(new MainMenuState());
        }
    }
}