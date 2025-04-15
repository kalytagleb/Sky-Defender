package service.game_state;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;

import java.awt.*;

public class GameStateManager {

    private IGameState currentState;

    public void setCurrentState(IGameState state) {
        this.currentState = state;
    }

    public IGameState getCurrentState() {
        return currentState;
    }

    public void update(GameContext context) {
        currentState.update(context);
    }

    public void render(Graphics2D g2, GameRenderer renderer, GameContext context) {
        currentState.render(g2, renderer, context);
    }
}