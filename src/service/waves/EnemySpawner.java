package service.waves;

import factory.enemy.BasicEnemyFactory;
import factory.enemy.EnemyFactoryRegister;
import factory.enemy.FastEnemyFactory;
import factory.enemy.TankEnemyFactory;
import model.enemies.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Spawns enemies for game waves based on wave number and screen dimensions.
 */
public class EnemySpawner {
    /** The screen width */
    private final int screenWidth;
    /** The screen height */
    private final int screenHeight;
    /** Random number generator for enemy placement and type */
    private final Random random = new Random();

    /** Registry for enemy factories */
    private final EnemyFactoryRegister<AbstractEnemy> factoryRegister = new EnemyFactoryRegister<>();

    /**
     * Constructs an enemy spawner with the specified screen dimensions.
     *
     * @param screenWidth the width of the game screen
     * @param screenHeight the height of the game screen
     */
    public EnemySpawner(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        factoryRegister.register(EnemyType.BASIC, new BasicEnemyFactory());
        factoryRegister.register(EnemyType.FAST, new FastEnemyFactory());
        factoryRegister.register(EnemyType.TANK, new TankEnemyFactory());
    }

    /**
     * Spawns a wave of enemies based on the wave number.
     *
     * @param waveNumber the current wave number
     * @return a list of spawned enemies
     */
    public List<AbstractEnemy> spawnWave(int waveNumber) {
        int count = 3 + waveNumber * 2;
        float speed = 0.5f + waveNumber * 0.1f;
        List<AbstractEnemy> result = new ArrayList<>();

        for (int i=0; i<count; i++) {
            int y = random.nextInt(screenHeight - 50) + 25;
            boolean fromLeft = i % 2 == 0;
            int x = fromLeft ? 0 : screenWidth;
            float angle = fromLeft ? 0f : 180f;

            EnemyType type = generateRandomType();
            AbstractEnemy enemy = factoryRegister.create(type, x, y, angle, speed);
            result.add(enemy);
        }
        return result;
    }

    /**
     * Generates a random enemy type based on probability.
     *
     * @return the randomly selected enemy type
     */
    private EnemyType generateRandomType() {
        int r = random.nextInt(100);
        if (r < 60) return EnemyType.BASIC;
        if (r < 85) return EnemyType.FAST;
        return EnemyType.TANK;
    }
}