package factory.enemy;

import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;

/**
 * Factory for creating BasicEnemy instances.
 */
public class BasicEnemyFactory implements EnemyFactory {
    @Override
    public AbstractEnemy create(int x, int y, float angle, float speed) {
        AbstractEnemy enemy = new BasicEnemy(x, y);
        enemy.setAngle(angle);
        enemy.setSpeed(speed);
        return enemy;
    }
}