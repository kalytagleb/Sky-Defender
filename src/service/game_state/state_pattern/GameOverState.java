package service.game_state.state_pattern;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import service.game_state.IGameState;

import java.awt.*;

public class GameOverState implements IGameState {

    @Override
    public void update(GameContext context) {}

    @Override
    public void render(Graphics2D g2, GameRenderer renderer, GameContext context) {
        int score = context.getScore();
        renderer.drawGameOver(g2, context.getWidth(), context.getHeight(), score);
    }
}