package model.enemies;

import logic_units.EnemyShapeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

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
        super(x, y, SIZE, 100, 0.4f, "/images/enemy_basic.png", EnemyShapeUtil.createDefaultEnemyShape(SIZE));
    }
}