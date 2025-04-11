package service.waves;

import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.enemies.TankEnemy;

import java.util.List;
import java.util.Random;

public class WaveManager {
    private final List<AbstractEnemy> enemies;
    private final EnemySpawner spawnerService;

    private int waveNumber = 1;
    private long waveStartTime;
    private boolean waveInProgress = false;
    private boolean waveShowText = true;

    public WaveManager(List<AbstractEnemy> enemies, int screenWidth, int screenHeight) {
        this.enemies = enemies;
        this.spawnerService = new EnemySpawner(screenWidth, screenHeight);
    }

    public void trySpawnWave(int score) {
        if (!waveInProgress && enemies.isEmpty()) {
            spawnNewWave(score);
        }
    }

    private void spawnNewWave(int score) {
        waveInProgress = true;
        waveShowText = true;
        waveStartTime = System.currentTimeMillis();
        waveNumber = 1 + score / 10;

        enemies.addAll(spawnerService.spawnWave(waveNumber));
    }

    public boolean shouldShowWaveText() {
        return waveShowText;
    }

    public void updateWaveTextState() {
        if (!waveShowText) return;
        if ((System.currentTimeMillis() - waveStartTime) >= 2000) {
            waveShowText = false;
            waveInProgress = false;
        }
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}