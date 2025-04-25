package service.waves;

import model.enemies.AbstractEnemy;

import java.util.List;

/**
 * Manages enemy waves, including spawning and wave text display.
 */
public class WaveManager {
    /** The list of active enemies */
    private final List<AbstractEnemy> enemies;
    /** The enemy spawner for creating new enemies */
    private final EnemySpawner spawner;

    /** The current wave number */
    private int waveNumber = 1;
    /** The start time of the current wave */
    private long waveStartTime;
    /** Flag indicating if wave text should be displayed */
    private boolean waveShowText = false;

    /** The interval between waves in milliseconds */
    private static final long WAVE_INTERVAL = 10000;
    /** The duration to display wave text in milliseconds */
    private static final long WAVE_TEXT_DURATION = 2000;

    /**
     * Constructs a wave manager with the specified enemy list and screen dimensions.
     *
     * @param enemies the list of active enemies
     * @param screenWidth the width of the game screen
     * @param screenHeight the height of the game screen
     */
    public WaveManager(List<AbstractEnemy> enemies, int screenWidth, int screenHeight) {
        this.enemies = enemies;
        this.spawner = new EnemySpawner(screenWidth, screenHeight);
    }

    /**
     * Starts the next wave of enemies based on the player's score.
     *
     * @param score the current player score
     */
    public void startNextWave(int score) {
        waveNumber = 1 + score / 10;
        waveShowText = true;
        waveStartTime = System.currentTimeMillis();

        List<AbstractEnemy> newWave = spawner.spawnWave(waveNumber);
        enemies.addAll(newWave);
    }

    /**
     * Updates the state of wave text display based on elapsed time.
     */
    public void updateWaveTextState() {
        if (!waveShowText) return;

        long time = System.currentTimeMillis() - waveStartTime;
        if (time >= 2000) {
            waveShowText = false;
        }
    }

    /**
     * Checks if the wave text should be displayed.
     *
     * @return true if wave text should be displayed, false otherwise
     */
    public boolean shouldShowWaveText() {
        return waveShowText;
    }

    /**
     * Gets the current wave number.
     *
     * @return the wave number
     */
    public int getWaveNumber() {
        return waveNumber;
    }

    /**
     * Sets the current wave number.
     *
     * @param waveNumber the new wave number
     */
    public void setWaveNumber(int waveNumber) {
        this.waveNumber = waveNumber;
    }
}