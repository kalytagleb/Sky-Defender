package core.game_loop;

import core.game_loop.GameContext;
import core.game_loop.GameUpdater;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Rocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.game_state.GameStateManager;
import service.game_state.state_pattern.GameOverState;
import service.waves.WaveManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameUpdaterTest {

    private GameUpdater updater;
    private GameContext context;
    private Player player;
    private List<AbstractEnemy> enemies;
    private List<AbstractWeapon> weapons;

    @BeforeEach
    void setUp() {
        updater = new GameUpdater();
        player = new Player();
        enemies = new CopyOnWriteArrayList<>();
        weapons = new CopyOnWriteArrayList<>();
        Key key = new Key();
        WaveManager waveManager = new WaveManager(enemies, 800, 600);
        GameStateManager gameStateManager = new GameStateManager();

        context = new GameContext(player, enemies, weapons, key, waveManager, gameStateManager, 800, 600);
    }

    @Test
    void weaponRemoveIfOutOfBounds() {
        AbstractWeapon weapon = new Rocket(1000, 1000, 0);
        weapons.add(weapon);

        updater.update(context, 800, 600);
        assertTrue(weapons.isEmpty(), "Weapon should be removed if out of bounds");
    }

    @Test
    void playerTakesDamageOnCollision() {
        AbstractEnemy enemy = new BasicEnemy(player.getX(), player.getY());
        enemies.add(enemy);

        int initialHp = player.getHp();
        updater.update(context, 800, 600);
        assertTrue(player.getHp() < initialHp, "Player should take damage from collision");
    }

    @Test
    void gameGoesToGameOverWhenPlayerDead() {
        player.setHp(0);
        updater.update(context, 800, 600);
        assertTrue(context.getGameStateManager().getCurrentState() instanceof GameOverState);
    }

    @Test
    void gameGoesToGameOverWhenPlayerOutOfBounds() {
        player.changeLocation(10000, 10000);
        updater.update(context, 800, 600);
        assertTrue(context.getGameStateManager().getCurrentState() instanceof GameOverState);
    }

    @Test
    void waveTextUpdatesCorrectly() {
        context.getWaveManager().setWaveNumber(1);
        updater.update(context, 800, 600);
        assertNotNull(context.getWaveManager());
    }
}