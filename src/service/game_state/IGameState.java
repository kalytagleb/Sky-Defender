package service.game_state;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;

import java.awt.*;

public interface IGameState {
    void update(GameContext context);
    void render(Graphics2D g2, GameRenderer renderer, GameContext context);
}