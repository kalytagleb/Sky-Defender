package data;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import data.enemy.EnemyData;
import data.weapon.WeaponData;
import exceptions.GameLoadException;
import exceptions.GameSaveException;
import factory.weapon.FlameFactory;
import factory.weapon.LaserFactory;
import factory.weapon.RocketFactory;
import model.Player;
import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.enemies.FastEnemy;
import model.enemies.TankEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Flame;
import model.weapon.Rocket;
import service.collision.PlayerCollisionService;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages saving and loading the game state to/from a file.
 */
public class SaveManager {

    private static final Logger LOGGER = Logger.getLogger(SaveManager.class.getName());
    private static final String SAVE_FILE = "save.txt";

    /**
     * Saves the current game state to a file.
     *
     * @param context the game context
     * @param renderer the game renderer
     * @param width the screen width
     * @throws GameSaveException if saving files
     */
    public void save(GameContext context, GameRenderer renderer, int width) throws GameSaveException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            Player player = context.getPlayer();

            List<EnemyData> enemyDataList = context.getEnemies().stream()
                            .map(e -> new EnemyData(
                                    e.getClass().getSimpleName(),
                                    e.getX(), e.getY(), e.getAngle(), e.getHp()))
                            .toList();

            List<WeaponData> weaponDataList = context.getWeapons().stream()
                            .map(w -> new WeaponData(
                                    w.getClass().getSimpleName(),
                                    w.getX(), w.getY(), w.getAngle(), w.getDamage()))
                            .toList();

            String weaponFactoryType = context.getWeaponFactory().getClass().getSimpleName();

            SaveData data = new SaveData(
                    context.getScore(),
                    context.getWaveManager().getWaveNumber(),
                    player.getHp(),
                    player.getX(),
                    player.getY(),
                    player.getAngle(),
                    weaponFactoryType,
                    enemyDataList,
                    weaponDataList
            );

            out.writeObject(data);
            LOGGER.info("Game saved successfully to " + SAVE_FILE);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save the game", e);
            throw new GameSaveException("We can't save the game: " + e.getMessage());
            // renderer.showNotification("We can't save the game!");
        }
    }

    /**
     * Asynchronously saves the game state to a file.
     *
     * @param context the game context
     * @param renderer the game renderer
     * @param width the screen width
     */
    public void asyncSave(GameContext context, GameRenderer renderer, int width) {
        new Thread(() -> {
            try {
                save(context, renderer, width);
            } catch (GameSaveException e) {
                renderer.showNotification("Async Save Error " + e.getMessage());
                LOGGER.warning("Async Error");
            }
        }, "SaveAsync").start();
    }

    /**
     * Loads the game state from a file.
     *
     * @param context the game context
     * @param renderer the game renderer
     * @param width the screen width
     * @throws GameLoadException if loading files
     */
    public void load(GameContext context, GameRenderer renderer, int width) throws GameLoadException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            SaveData data = (SaveData) in.readObject();

            context.setScore(data.getScore());
            context.getWaveManager().setWaveNumber(data.getWave());

            Player player = context.getPlayer();
            player.setHp(data.getPlayerHp());
            player.changeLocation(data.getPlayerX(), data.getPlayerY());
            player.setAngle(data.getPlayerAngle());

            context.setWeaponFactory(switch (data.getWeaponFactoryType()) {
                case "RocketFactory" -> new RocketFactory();
                case "FlameFactory" -> new FlameFactory();
                case "LaserFactory" -> new LaserFactory();
                default -> new RocketFactory();
            });

            // Restore enemies
            context.getEnemies().clear();
            for (EnemyData ed : data.getEnemies()) {
                AbstractEnemy enemy = switch (ed.getType()) {
                    case "BasicEnemy" -> new BasicEnemy(ed.getX(), ed.getY());
                    case "FastEnemy" -> new FastEnemy(ed.getX(), ed.getY());
                    case "TankEnemy" -> new TankEnemy(ed.getX(), ed.getY());
                    default -> null;
                };
                if (enemy != null) {
                    enemy.setHp(ed.getHp());
                    enemy.setAngle(ed.getAngle());
                    context.getEnemies().add(enemy);
                }
            }

            // Restore weapons
            context.getWeapons().clear();
            for (WeaponData wd : data.getWeapons()) {
                AbstractWeapon weapon = switch (wd.getType()) {
                    case "Rocket" -> new Rocket(wd.getX(), wd.getY(), wd.getAngle());
                    case "Flame" -> new Flame(wd.getX(), wd.getY(), wd.getAngle());
                    case "Laser" -> new Flame(wd.getX(), wd.getY(), wd.getAngle());
                    default -> null;
                };
                if (weapon != null) {
                    weapon.setDamage(wd.getDamage());
                    context.getWeapons().add(weapon);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Failed to load the game", e);
            throw new GameLoadException("Save file not found.");
        }
    }
}