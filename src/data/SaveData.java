package data;

import data.enemy.EnemyData;
import data.weapon.WeaponData;

import java.io.Serializable;
import java.util.List;

public class SaveData implements Serializable {
    private static final long serial = 1L;

    private final int score;
    private final int wave;
    private final int playerHp;
    private final double playerX;
    private final double playerY;
    private final float playerAngle;
    private final String weaponFactoryType;

    private final List<EnemyData> enemies;
    private final List<WeaponData> weapons;

    public SaveData(int score, int wave, int playerHp, double playerX, double playerY,
                    float playerAngle, String weaponFactoryType,
                    List<EnemyData> enemies, List<WeaponData> weapons) {
        this.score = score;
        this.wave = wave;
        this.playerHp = playerHp;
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerAngle = playerAngle;
        this.weaponFactoryType = weaponFactoryType;
        this.enemies = enemies;
        this.weapons = weapons;
    }

    public int getScore() {
        return score;
    }

    public int getWave() {
        return wave;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerY() {
        return playerY;
    }

    public float getPlayerAngle() {
        return playerAngle;
    }

    public String getWeaponFactoryType() {
        return weaponFactoryType;
    }

    public List<EnemyData> getEnemies() {
        return enemies;
    }

    public List<WeaponData> getWeapons() {
        return weapons;
    }
}