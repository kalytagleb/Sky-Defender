package core.game_loop.panel;

import core.game_loop.GameContext;
import core.game_loop.GameLoop;
import core.game_loop.GameRenderer;
import data.score.HighScoreManager;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import service.game_state.GameStateManager;
import service.game_state.state_pattern.MainMenuState;
import service.waves.WaveManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Initializes game components such as context, renderer and game loop
 */

public class PanelInitializer {
    /** Default width of the game screen */
    public static final int WIDTH = 800;
    /** Default height of the game screen */
    public static final int HEIGHT = 600;

    /**
     * Creates the game context with initial player, enemies, and game state.
     *
     * @param key the keyboard main.input handler
     * @param gsm the game state manager
     * @param width the screen width
     * @param height the screen height
     * @return the initialized game context
     */
    public GameContext createContext(Key key, GameStateManager gsm, int width, int height) {
        Player player = new Player();
        player.changeLocation(width / 2.0, height / 2.0);
        player.setKey(key);

        List<AbstractEnemy> enemies = new CopyOnWriteArrayList<>();
        List<AbstractWeapon> weapons = new CopyOnWriteArrayList<>();
        WaveManager waveManager = new WaveManager(enemies, width, height);

        gsm.setCurrentState(new MainMenuState());
        System.out.println("State set to " + gsm.getCurrentState());

        GameContext context = new GameContext(player, enemies, weapons, key, waveManager, gsm, width, height);

        int loadedHighScore = HighScoreManager.load();
        context.setHighScore(loadedHighScore);

        return context;
    }

    /**
     * Creates the game renderer.
     *
     * @return the initialized game renderer
     */
    public GameRenderer createRenderer() {
        return new GameRenderer();
    }

    /**
     * Create the game loop with the specified panel, context, and renderer.
     *
     * @param panel the game panel
     * @param context the game context
     * @param renderer the game renderer
     * @return the initialized game loop
     */
    public GameLoop createGameLoop(PanelGame panel, GameContext context, GameRenderer renderer) {
        return new GameLoop(panel, context, renderer);
    }
}