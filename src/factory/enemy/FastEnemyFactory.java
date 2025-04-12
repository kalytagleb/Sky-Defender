package factory.enemy;

import model.enemies.AbstractEnemy;
import model.enemies.FastEnemy;

public class FastEnemyFactory implements EnemyFactory {
    @Override
    public AbstractEnemy create(int x, int y, float angle, float speed) {
        AbstractEnemy enemy = new FastEnemy(x, y);
        enemy.setAngle(angle);
        enemy.setSpeed(speed);
        return enemy;
    }
}