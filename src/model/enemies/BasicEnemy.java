package model.enemies;

import logic_units.EnemyShapeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

/**
 * BasicEnemy is a standard type of enemy that appears in the game.
 * <p>
 * It extends {@link AbstractEnemy} and defines default attributes such as
 * health, speed, appearance, and shape used for collision detection.
 * This enemy serves as a baseline for other enemy types like {@link FastEnemy} and {@link TankEnemy}.
 */
public class BasicEnemy extends AbstractPolygonEnemy {
    public static final double SIZE = 50;

    public BasicEnemy(double x, double y) {
        super(x, y, SIZE, 100, 0.4f, "/images/enemy_basic.png", EnemyShapeUtil.createDefaultEnemyShape(SIZE));
    }
}