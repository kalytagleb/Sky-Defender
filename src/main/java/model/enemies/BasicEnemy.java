package model.enemies;

import logic_units.EnemyShapeUtil;

/**
 * Represents a basic enemy type.
 */
public class BasicEnemy extends AbstractPolygonEnemy {
    public static final double SIZE = 50;

    /**
     * Constructs a basic enemy at the specified position.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public BasicEnemy(double x, double y) {
        super(x, y, SIZE, 100, 0.4f, "/enemy_basic.png", EnemyShapeUtil.createDefaultEnemyShape(SIZE));
    }
}