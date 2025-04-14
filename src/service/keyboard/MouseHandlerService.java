package service.keyboard;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import service.game_state.GameState;
import service.game_state.GameStateManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseHandlerService {
    public void handleClick(MouseEvent e, GameContext context, GameRenderer renderer) {
        if (e.getID() != MouseEvent.MOUSE_CLICKED) return;

        Point p = e.getPoint();
        GameStateManager gsm = context.getGameStateManager();

        if (gsm.is(GameState.MAIN_MENU) && renderer.getManualButtonBounds().contains(p)) {
            gsm.setState(GameState.MANUAL);
        }

        if (gsm.is(GameState.MANUAL) && renderer.getManualBackButtonBounds().contains(p)) {
            gsm.setState(GameState.MAIN_MENU);
        }
    }
}