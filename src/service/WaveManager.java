package service;

import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.enemies.TankEnemy;

import java.util.List;
import java.util.Random;

public class WaveManager {
    private final List<AbstractEnemy> enemies;
    private final int width;
    private final int height;

    private int waveNumber = 1;
    private long waveStartTime;
    private boolean waveInProgress = false;
    private boolean waveShowText = true;

    public WaveManager(List<AbstractEnemy> enemies, int width, int height) {
        this.enemies = enemies;
        this.width = width;
        this.height = height;
    }

    public void trySpawnWave(int score) {
        if (!waveInProgress && enemies.isEmpty()) {
            spawnWave(score);
        }
    }

    /**
     * Spawns a new wave of enemies based on score and wave number.
     * Adjusts difficulty dynamically by increasing speed and count.
     */
    private void spawnWave(int score) {
        waveInProgress = true;
        waveShowText = true;
        waveStartTime = System.currentTimeMillis();
        waveNumber = 1 + score / 10;

        int enemiesToSpawn = 3 + waveNumber * 2;
        float baseSpeed = 0.3f + waveNumber * 0.1f;
        Random random = new Random();

        for (int i=0; i<enemiesToSpawn; i++) {
            int y = random.nextInt(height-50) + 25;
            boolean fromLeft = i % 2 == 0;
            int x = fromLeft ? 0 : width;
            float angle = fromLeft ? 0f : 180f;

            AbstractEnemy enemy;
            int type = random.nextInt(100);
            if (type < 60) {
                enemy = new BasicEnemy(x, y);
            } else if (type < 85) {
                enemy = new BasicEnemy(x, y);
            } else {
                enemy = new TankEnemy(x, y);
            }

            enemy.setAngle(angle);
            enemy.setSpeed(baseSpeed);
            enemies.add(enemy);
        }
    }

    public boolean shouldShowWaveText() {
        return waveShowText && (System.currentTimeMillis() - waveStartTime) < 2000;
    }

    public void updateWaveTextState() {
        if ((System.currentTimeMillis() - waveStartTime) >= 2000) {
            waveShowText = false;
            waveInProgress = false;
        }
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}