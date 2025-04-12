package factory.enemy;

import model.enemies.AbstractEnemy;
import model.enemies.EnemyType;

import java.util.HashMap;
import java.util.Map;

public class EnemyFactoryRegister {
    private final Map<EnemyType, EnemyFactory> factories = new HashMap<>();

    public EnemyFactoryRegister() {
        factories.put(EnemyType.BASIC, new BasicEnemyFactory());
        factories.put(EnemyType.FAST, new FastEnemyFactory());
        factories.put(EnemyType.TANK, new TankEnemyFactory());
    }

    public AbstractEnemy create(EnemyType type, int x, int y, float angle, float speed) {
        return factories.get(type).create(x, y, angle, speed);
    }
}