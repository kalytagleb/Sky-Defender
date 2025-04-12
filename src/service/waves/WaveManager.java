package service.waves;

import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.enemies.TankEnemy;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.List;
import java.util.Random;

public class WaveManager {
    private final List<AbstractEnemy> enemies;
    private final EnemySpawner spawner;

    private int waveNumber = 1;
    private long waveStartTime;
    private boolean waveShowText = false;

    private static final long WAVE_INTERVAL = 10000;
    private static final long WAVE_TEXT_DURATION = 2000;

    public WaveManager(List<AbstractEnemy> enemies, int screenWidth, int screenHeight) {
        this.enemies = enemies;
        this.spawner = new EnemySpawner(screenWidth, screenHeight);
    }

    public void startNextWave(int score) {
        waveNumber = 1 + score / 10;
        waveShowText = true;
        waveStartTime = System.currentTimeMillis();

        List<AbstractEnemy> newWave = spawner.spawnWave(waveNumber);
        enemies.addAll(newWave);
    }

    public void updateWaveTextState() {
        if (!waveShowText) return;

        long time = System.currentTimeMillis() - waveStartTime;
        if (time >= 2000) {
            waveShowText = false;
        }
    }

    public boolean shouldShowWaveText() {
        return waveShowText;
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}