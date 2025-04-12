package model.enemies;

import logic_units.EnemyShapeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * FastEnemy is a subclass of {@link BasicEnemy} that moves faster but has lower health.
 * <p>
 * This type of enemy adds challenge by reaching the player more quickly than regular enemies.
 * It uses a distinct image and retains the same shape as {@link BasicEnemy} for collision.
 */
public class FastEnemy extends AbstractPolygonEnemy {

    public static final double SIZE = 50;

    public FastEnemy(double x, double y) {
        super(x, y, SIZE, 60, 0.6f, "/images/FastEnemy.png", EnemyShapeUtil.createDefaultEnemyShape(SIZE));
    }
}