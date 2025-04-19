package factory.enemy;

import model.enemies.AbstractEnemy;
import model.enemies.EnemyType;

import java.util.HashMap;
import java.util.Map;

/**
 * Registers and manages enemy factories for different enemy types.
 */
public class EnemyFactoryRegister {
    private final Map<EnemyType, EnemyFactory> factories = new HashMap<>();

    /**
     * Constructs the enemy factory register and initializes factory.
     */
    public EnemyFactoryRegister() {
        factories.put(EnemyType.BASIC, new BasicEnemyFactory());
        factories.put(EnemyType.FAST, new FastEnemyFactory());
        factories.put(EnemyType.TANK, new TankEnemyFactory());
    }

    /**
     * Creates an enemy of the specified type at the given position.
     *
     * @param type the enemy type
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param angle the rotation angle
     * @param speed the movement speed
     * @return the created enemy
     */
    public AbstractEnemy create(EnemyType type, int x, int y, float angle, float speed) {
        return factories.get(type).create(x, y, angle, speed);
    }
}