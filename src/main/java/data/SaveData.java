package data;

import data.enemy.EnemyData;
import data.weapon.WeaponData;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the main.core.data structure for saving game state.
 * Implements Serializable to enable saving to a file.
 */

public class SaveData implements Serializable {
    private static final long serial = 1L;

    private final int score;
    private final int wave;
    private final int playerHp;
    private final double playerX;
    private final double playerY;
    private final float playerAngle;
    private final String weaponFactoryType;
    private final int highScore;

    private final List<EnemyData> enemies;
    private final List<WeaponData> weapons;

    /**
     * Constructs a SaveData object with the current game state.
     *
     * @param score the current score
     * @param wave the current wave number
     * @param playerHp the player's health points
     * @param playerX the player's x-coordinate
     * @param playerY the player's y-coordinate
     * @param playerAngle the player's rotation angle
     * @param weaponFactoryType the type of weapon main.factory
     * @param enemies the list of enemy main.core.data
     * @param weapons the list of weapon main.core.data
     */
    public SaveData(int score, int wave, int playerHp, double playerX, double playerY,
                    float playerAngle, String weaponFactoryType, int highScore,
                    List<EnemyData> enemies, List<WeaponData> weapons) {
        this.score = score;
        this.wave = wave;
        this.playerHp = playerHp;
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerAngle = playerAngle;
        this.weaponFactoryType = weaponFactoryType;
        this.highScore = highScore;
        this.enemies = enemies;
        this.weapons = weapons;
    }

    /**
     * Gets the saved score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the saved wave number.
     *
     * @return the wave number
     */
    public int getWave() {
        return wave;
    }

    /**
     * Gets the saved player health points.
     *
     * @return the player's health points
     */
    public int getPlayerHp() {
        return playerHp;
    }

    /**
     * Gets the saved player x-coordinate
     *
     * @return the player's x-coordinate
     */
    public double getPlayerX() {
        return playerX;
    }

    /**
     * Gets the saved player y-coordinate
     *
     * @return the player's y-coordinate
     */
    public double getPlayerY() {
        return playerY;
    }

    /**
     * Gets the saved player rotation angle.
     *
     * @return the player's angle
     */
    public float getPlayerAngle() {
        return playerAngle;
    }

    /**
     * Gets the saved weapon main.factory type.
     *
     * @return the weapon main.factory type
     */
    public String getWeaponFactoryType() {
        return weaponFactoryType;
    }

    /**
     * Gets the highest score saved in the save file.
     *
     * @return the high score
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Gets the saved list of enemy main.core.data.
     *
     * @return the list of enemy main.core.data.
     */
    public List<EnemyData> getEnemies() {
        return enemies;
    }

    /**
     * Gets the saved list of weapon main.core.data.
     *
     * @return the list of weapon main.core.data.
     */
    public List<WeaponData> getWeapons() {
        return weapons;
    }
}