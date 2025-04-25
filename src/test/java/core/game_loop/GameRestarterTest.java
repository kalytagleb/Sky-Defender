package core.game_loop;

import core.game_loop.GameContext;
import core.game_loop.GameRestarter;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Rocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.game_state.GameStateManager;
import service.waves.WaveManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameRestarterTest {

    private GameContext context;
    private Player player;
    private List<AbstractEnemy> enemies;
    private List<AbstractWeapon> weapons;
    private GameRestarter restarter;

    @BeforeEach
    void setUp() {
        player = new Player();
        player.setHp(40);
        player.setAngle(30);
        player.changeLocation(100, 100);

        enemies = new CopyOnWriteArrayList<>();
        enemies.add(new BasicEnemy(0, 0));

        weapons = new CopyOnWriteArrayList<>();
        weapons.add(new Rocket(0, 0, 0f));

        Key key = new Key();
        WaveManager waveManager = new WaveManager(enemies, 800, 600);
        GameStateManager gsm = new GameStateManager();

        context = new GameContext(player, enemies, weapons, key, waveManager, gsm, 800, 600);
        context.setScore(42);

        restarter = new GameRestarter();
    }

    @Test
    void restartShouldResetGameState() {
        restarter.restart(context, 800, 600);

        assertEquals(0, context.getScore(), "Score should be reset to 0");
        assertTrue(context.getEnemies().isEmpty(), "Enemies should be cleared");
        assertTrue(context.getWeapons().isEmpty(), "Weapons should be cleared");

        assertEquals(800 / 2.0, context.getPlayer().getX());
        assertEquals(600 / 2.0, context.getPlayer().getY());
        assertEquals(0, context.getPlayer().getAngle());
        assertEquals(100, context.getPlayer().getHp());
    }
}