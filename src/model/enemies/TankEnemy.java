package model.enemies;

import logic_units.EnemyShapeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Represents a tank-like enemy with high health.
 */
public class TankEnemy extends AbstractPolygonEnemy {
    public static final double SIZE = 50;

    /**
     * Constructs a tank enemy at the specified position.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public TankEnemy(double x, double y) {
        super(x, y, SIZE, 300, 0.25f, "/images/TankEnemy.png", EnemyShapeUtil.createDefaultEnemyShape(SIZE));
    }
}