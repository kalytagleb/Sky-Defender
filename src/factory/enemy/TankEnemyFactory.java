package factory.enemy;

import jdk.jfr.Percentage;
import model.enemies.AbstractEnemy;
import model.enemies.TankEnemy;

public class TankEnemyFactory implements EnemyFactory {
    @Override
    public AbstractEnemy create(int x, int y, float angle, float speed) {
        AbstractEnemy enemy = new TankEnemy(x, y);
        enemy.setAngle(angle);
        enemy.setSpeed(speed);
        return enemy;
    }
}