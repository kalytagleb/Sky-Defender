package service.waves;

import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.enemies.FastEnemy;
import model.enemies.TankEnemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner {
    private final int screenWidth;
    private final int screenHeight;
    private final Random random = new Random();

    public EnemySpawner(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public List<AbstractEnemy> spawnWave(int waveNumber) {
        int count = 3 + waveNumber * 2;
        float speed = 0.3f + waveNumber * 0.1f;
        List<AbstractEnemy> result = new ArrayList<>();

        for (int i=0; i<count; i++) {
            int y = random.nextInt(screenHeight - 50) + 25;
            boolean fromLeft = i % 2 == 0;
            int x = fromLeft ? 0 : screenWidth;
            float angle = fromLeft ? 0f: 180f;

            AbstractEnemy enemy = generateRandomEnemy(x, y);
            enemy.setAngle(angle);
            enemy.setSpeed(speed);
            result.add(enemy);
        }
        return result;
    }

    private AbstractEnemy generateRandomEnemy(int x, int y) {
        int type = random.nextInt(100);
        if (type < 60) return new BasicEnemy(x, y);
        if (type < 85) return new FastEnemy(x, y);
        return new TankEnemy(x, y);
    }
}