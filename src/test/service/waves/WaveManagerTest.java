package test.service.waves;

import model.enemies.AbstractEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.waves.WaveManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WaveManagerTest {

    private WaveManager waveManager;
    private List<AbstractEnemy> enemies;

    @BeforeEach
    void setUp() {
        enemies = new CopyOnWriteArrayList<>();
        waveManager = new WaveManager(enemies, 800, 600);
    }

    @Test
    void startNextWaveSpawnsEnemies() {
        waveManager.startNextWave(0);
        assertFalse(enemies.isEmpty(), "Enemies should be added when wave starts");
    }

    @Test
    void waveNumberIncreaseWithScore() {
        waveManager.startNextWave(20);
        assertEquals(3, waveManager.getWaveNumber(), "Wave number should be calculated");
    }

    @Test
    void waveTextShownAfterWaveStarts() {
        waveManager.startNextWave(0);
        assertTrue(waveManager.shouldShowWaveText(), "Wave text should be shown after wave starts");
    }

    @Test
    void setWaveNumberWorksCorrectly() {
        waveManager.setWaveNumber(5);
        assertEquals(5, waveManager.getWaveNumber());
    }
}