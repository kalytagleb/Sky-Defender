package model.enemies;

import logic_units.EnemyShapeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * TankEnemy is a subclass of {@link BasicEnemy} that moves slowly but has significantly more health.
 * <p>
 * This enemy type is designed to absorb more hits, making it a tank-like unit on the battlefield.
 * It uses a custom image and the same collision shape as {@link BasicEnemy}.
 */
public class TankEnemy extends AbstractPolygonEnemy {

    public static final double SIZE = 50;

    public TankEnemy(double x, double y) {
        super(x, y, SIZE, 300, 0.25f, "/images/TankEnemy.png", EnemyShapeUtil.createDefaultEnemyShape(SIZE));
    }
}