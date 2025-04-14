package test.data;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import data.SaveManager;
import exceptions.GameLoadException;
import exceptions.GameSaveException;
import factory.weapon.RocketFactory;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Rocket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.game_state.GameStateManager;
import service.waves.WaveManager;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SaveManagerTest {

    private SaveManager saveManager;
    private GameContext context;
    private File saveFile = new File("save.txt");

    @BeforeEach
    void setUp() {
        saveManager = new SaveManager();

        Player player = new Player();
        player.changeLocation(100, 200);
        player.setAngle(90);
        player.setHp(80);

        List<AbstractEnemy> enemies = new CopyOnWriteArrayList<>();
        enemies.add(new BasicEnemy(300, 300));

        List<AbstractWeapon> weapons = new CopyOnWriteArrayList<>();
        weapons.add(new Rocket(150, 150, 45f));

        WaveManager waveManager = new WaveManager(enemies, 800, 600);
        GameStateManager gsm = new GameStateManager();
        Key key = new Key();

        context = new GameContext(player, enemies, weapons, key, waveManager, gsm, 800, 600);
        context.setScore(42);
        context.setWeaponFactory(new RocketFactory());
    }

    @AfterEach
    void tearDown() {
        saveFile.delete();
    }

    @Test
    void savesFileSuccessfully() {
        assertDoesNotThrow(() -> saveManager.save(context, new GameRenderer(), 800));
        assertTrue(saveFile.exists(), "Save file should be created");
    }

    @Test
    void loadsDataCorrectly() throws GameSaveException, GameLoadException {
        saveManager.save(context, new GameRenderer(), 800);

        context.setScore(0);
        context.getPlayer().setHp(1);
        context.getPlayer().changeLocation(0, 0);
        context.getEnemies().clear();
        context.getWeapons().clear();

        saveManager.load(context, new GameRenderer(), 800);

        assertEquals(42, context.getScore());
        assertEquals(80, context.getPlayer().getHp());
        assertEquals(100, context.getPlayer().getX());
        assertEquals(1, context.getEnemies().size());
        assertEquals(1, context.getWeapons().size());
    }
}