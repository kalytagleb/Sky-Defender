package service.game_state.state_pattern;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import core.game_loop.GameUpdater;
import service.game_state.IGameState;

import java.awt.*;

public class PlayingState implements IGameState {

    private final GameUpdater updater = new GameUpdater();

    @Override
    public void update(GameContext context) {
        updater.update(context, context.getWidth(), context.getHeight());
    }

    @Override
    public void render(Graphics2D g2, GameRenderer renderer, GameContext context) {
        renderer.drawGame(g2, context, context.getWidth(), context.getHeight());
    }
}