package model.enemies;

import logic_units.EnemyShapeUtil;

/**
 * Represents a fast-moving enemy type.
 */
public class FastEnemy extends AbstractPolygonEnemy {

    public static final double SIZE = 50;

    /**
     * Constructs a fast enemy at the specified position.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public FastEnemy(double x, double y) {
        super(x, y, SIZE, 60, 1.5f, "/FastEnemy.png", EnemyShapeUtil.createDefaultEnemyShape(SIZE));
    }
}