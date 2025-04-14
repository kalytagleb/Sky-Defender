package test.service.waves;

import model.enemies.AbstractEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.waves.EnemySpawner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnemySpawnerTest {

    private EnemySpawner spawner;
    private final int screenWidth = 800;
    private final int screenHeight = 600;

    @BeforeEach
    void setUp() {
        spawner = new EnemySpawner(800, 600);
    }

    @Test
    void spawnsCorrectNumberOfEnemies() {
        int waveNumber = 3;
        List<AbstractEnemy> enemies = spawner.spawnWave(waveNumber);

        int expectedCount = 3 + waveNumber * 2;
        assertEquals(expectedCount, enemies.size(), "Should spawn correct numbers of enemies");
    }

    @Test
    void spawnEnemiesWithinValidYBounds() {
        List<AbstractEnemy> enemies = spawner.spawnWave(1);
        for (AbstractEnemy enemy : enemies) {
            int y = (int) enemy.getY();
            assertTrue(y >= 25 && y <= screenHeight - 25, "Enemy Y should be within vertical screen bounds");
        }
    }

    @Test
    void spawnEnemiesAtScreenEdges() {
        List<AbstractEnemy> enemies = spawner.spawnWave(1);
        for (int i=0; i<enemies.size(); i++) {
            int x = (int) enemies.get(i).getX();
            if (i%2 == 0) {
                assertEquals(0, x, "Even index enemies should spawn on the left edge");
            } else {
                assertEquals(screenWidth, x, "Odd index enemies should spawn on the right edge");
            }
        }
    }

    @Test
    void enemiesHaveCorrectSpeedAndAngle() {
        int waveNumber = 5;
        float expectedSpeed = 0.5f + waveNumber * 0.1f;

        List<AbstractEnemy> enemies = spawner.spawnWave(waveNumber);
        for (int i=0; i<enemies.size(); i++) {
            AbstractEnemy e = enemies.get(i);
            float expectedAngle = i % 2 == 0 ? 0f : 180f;

            assertEquals(expectedAngle, e.getAngle(), "Enemy angle should match direction");
            assertEquals(expectedSpeed, e.getSpeed(), "Enemy speed should scale with wave");
        }
    }
}