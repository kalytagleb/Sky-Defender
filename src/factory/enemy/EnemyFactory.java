package factory.enemy;

import model.enemies.AbstractEnemy;

public interface EnemyFactory {
    AbstractEnemy create(int x, int y, float angle, float speed);
}