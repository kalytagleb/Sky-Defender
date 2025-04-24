package core.game_loop;

import factory.weapon.RocketFactory;
import factory.weapon.WeaponFactory;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import service.game_state.GameStateManager;
import service.waves.WaveManager;

import java.util.List;

/**
 * Manages the game context, including player, enemies, weapons, and game state.
 */

public class GameContext {
    private final Player player;
    private final List<AbstractEnemy> enemies;
    private final List<AbstractWeapon> weapons;
    private final Key key;
    private final WaveManager waveManager;
    private final GameStateManager gameStateManager;
    private WeaponFactory<? extends AbstractWeapon> weaponFactory = new RocketFactory();

    private final int screenWidth;
    private final int screenHeight;

    private int score = 0;
    private int highScore = 0;

    /**
     * Construct the game context with all necessary components.
     *
     * @param player the player object
     * @param enemies list of enemies
     * @param weapons list of weapons
     * @param key the keyboard input handler
     * @param waveManager the wave manager
     * @param gameStateManager the game state manager
     * @param screenWidth the screen width
     * @param screenHeight the screen height
     */
    public GameContext(Player player,
                       List<AbstractEnemy> enemies,
                       List<AbstractWeapon> weapons,
                       Key key,
                       WaveManager waveManager,
                       GameStateManager gameStateManager,
                       int screenWidth,
                       int screenHeight) {
        this.player = player;
        this.enemies = enemies;
        this.weapons = weapons;
        this.key = key;
        this.waveManager = waveManager;
        this.gameStateManager = gameStateManager;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Gets the player object.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the list of enemies.
     *
     * @return the list of enemies
     */
    public List<AbstractEnemy> getEnemies() {
        return enemies;
    }

    /**
     * Gets the list of weapons.
     *
     * @return the list of weapons
     */
    public List<AbstractWeapon> getWeapons() {
        return weapons;
    }

    /**
     * Gets the keyboard input handler.
     *
     * @return the key handler
     */
    public Key getKey() {
        return key;
    }

    /**
     * Gets the wave manager.
     *
     * @return the wave manager
     */
    public WaveManager getWaveManager() {
        return waveManager;
    }

    /**
     * Gets the game state manager.
     *
     * @return the game state manager
     */
    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    /**
     * Gets the current score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the current score.
     *
     * @param score the new score value
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the current weapon factory.
     *
     * @return the weapon factory.
     */
    public WeaponFactory<? extends AbstractWeapon> getWeaponFactory() {
        return weaponFactory;
    }

    /**
     * Sets the weapon factory.
     *
     * @param weaponFactory the new weapon factory
     */
    public void setWeaponFactory(WeaponFactory<? extends AbstractWeapon> weaponFactory) {
        this.weaponFactory = weaponFactory;
    }

    /**
     * Gets the screen width.
     *
     * @return the screen width
     */
    public int getWidth() {
        return screenWidth;
    }

    /**
     * Gets the screen height.
     *
     * @return the screen height
     */
    public int getHeight() {
        return screenHeight;
    }

    /**
     * Gets the highest score ever.
     *
     * @return the high score
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Sets the highest score ever.
     *
     * @param highScore the new high score value
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}