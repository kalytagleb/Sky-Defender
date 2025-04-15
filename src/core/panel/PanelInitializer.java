package core.panel;

import core.game_loop.GameContext;
import core.game_loop.GameLoop;
import core.game_loop.GameRenderer;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import service.game_state.GameStateManager;
import service.game_state.state_pattern.MainMenuState;
import service.waves.WaveManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PanelInitializer {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public GameContext createContext(Key key, GameStateManager gsm, int width, int height) {
        Player player = new Player();
        player.changeLocation(width / 2.0, height / 2.0);
        player.setKey(key);

        List<AbstractEnemy> enemies = new CopyOnWriteArrayList<>();
        List<AbstractWeapon> weapons = new CopyOnWriteArrayList<>();
        WaveManager waveManager = new WaveManager(enemies, width, height);

        gsm.setCurrentState(new MainMenuState());

        return new GameContext(player, enemies, weapons, key, waveManager, gsm, width, height);
    }

    public GameRenderer createRenderer() {
        return new GameRenderer();
    }

    public GameLoop createGameLoop(PanelGame panel, GameContext context, GameRenderer renderer) {
        return new GameLoop(panel, context, renderer);
    }
}