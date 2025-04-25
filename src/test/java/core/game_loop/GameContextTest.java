package core.game_loop;

import core.game_loop.GameContext;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.game_state.GameStateManager;
import service.waves.WaveManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameContextTest {
    private GameContext context;
    private Player player;
    private List<AbstractEnemy> enemies;
    private List<AbstractWeapon> weapons;
    private Key key;
    private WaveManager waveManager;
    private GameStateManager gameStateManager;

    @BeforeEach
    void setUp() {
        player = new Player();
        enemies = new CopyOnWriteArrayList<>();
        weapons = new CopyOnWriteArrayList<>();
        key = new Key();
        waveManager = new WaveManager(enemies, 800, 600);
        gameStateManager = new GameStateManager();

        context = new GameContext(player, enemies, weapons, key, waveManager, gameStateManager, 800, 600);
    }

    @Test
    void contextInitializesCorrectly() {
        assertEquals(player, context.getPlayer());
        assertEquals(enemies, context.getEnemies());
        assertEquals(weapons, context.getWeapons());
        assertEquals(key, context.getKey());
        assertEquals(waveManager, context.getWaveManager());
        assertEquals(gameStateManager, context.getGameStateManager());
        assertEquals(0, context.getScore());
    }

    @Test
    void scoreCanBeUpdated() {
        context.setScore(42);
        assertEquals(42, context.getScore());
    }

    @Test
    void contextStoresScreenDimensions() {
        assertEquals(800, context.getWidth());
        assertEquals(600, context.getHeight());
    }
}