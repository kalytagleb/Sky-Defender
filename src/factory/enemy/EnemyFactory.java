package factory.enemy;

import model.enemies.AbstractEnemy;

/**
 * Interface for creating enemy instances.
 */
public interface EnemyFactory<T extends AbstractEnemy> {
    /**
     * Creates an enemy at the specified position with given angle and speed.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param angle the rotation angle
     * @param speed the movement speed
     * @return the created enemy
     */
    T create(int x, int y, float angle, float speed);
}