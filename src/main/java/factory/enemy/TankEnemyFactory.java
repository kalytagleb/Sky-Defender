package factory.enemy;

import model.enemies.AbstractEnemy;
import model.enemies.TankEnemy;

/**
 * Factory for creating TankEnemy instances.
 */
public class TankEnemyFactory implements EnemyFactory {
    @Override
    public AbstractEnemy create(int x, int y, float angle, float speed) {
        AbstractEnemy enemy = new TankEnemy(x, y);
        enemy.setAngle(angle);
        enemy.setSpeed(speed);
        return enemy;
    }
}