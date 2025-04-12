package service.waves;

import factory.enemy.EnemyFactoryRegister;
import model.enemies.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner {
    private final int screenWidth;
    private final int screenHeight;
    private final Random random = new Random();

    private final EnemyFactoryRegister factoryRegister = new EnemyFactoryRegister();

    public EnemySpawner(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public List<AbstractEnemy> spawnWave(int waveNumber) {
        int count = 3 + waveNumber * 2;
        float speed = 0.5f + waveNumber * 0.1f;
        List<AbstractEnemy> result = new ArrayList<>();

        for (int i=0; i<count; i++) {
            int y = random.nextInt(screenHeight - 50) + 25;
            boolean fromLeft = i % 2 == 0;
            int x = fromLeft ? 0 : screenWidth;
            float angle = fromLeft ? 0f: 180f;

            EnemyType type = generateRandomType();
            AbstractEnemy enemy = factoryRegister.create(type, x, y, angle, speed);
            result.add(enemy);
        }
        return result;
    }

    private EnemyType generateRandomType() {
        int r = random.nextInt(100);
        if (r < 60) return EnemyType.BASIC;
        if (r < 85) return EnemyType.FAST;
        return EnemyType.TANK;
    }
}