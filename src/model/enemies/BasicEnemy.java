package model.enemies;

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
public class BasicEnemy extends AbstractEnemy {

    /**
     * The size of the enemy's bounding box and image (in pixels).
     */
    public static final double SIZE = 50;

    /**
     * Constructs a new BasicEnemy at the given coordinates.
     *
     * @param x the starting X position
     * @param y the starting Y position
     */
    public BasicEnemy(double x, double y) {
        super(x, y);
        setHp(100);
        setSpeed(1.2f);
        setImage(new ImageIcon(getClass().getResource("/images/enemy_basic.png")).getImage());

        // Define the polygon shape used for collision
        Path2D p = new Path2D.Double();
        p.moveTo(0, SIZE / 2);
        p.lineTo(15, 10);
        p.lineTo(SIZE - 5, 13);
        p.lineTo(SIZE + 10, SIZE / 2);
        p.lineTo(SIZE - 5, SIZE - 13);
        p.lineTo(15, SIZE - 10);
        setShapeArea(new Area(p));
    }

    /**
     * Updates the position of the enemy based on its angle and speed.
     */
    @Override
    public void update() {
        double dx = Math.cos(Math.toRadians(getAngle())) * getSpeed();
        double dy = Math.sin(Math.toRadians(getAngle())) * getSpeed();
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * Draws the enemy on screen using its image and rotation.
     *
     * @param g2 the graphics context used for drawing
     */
    @Override
    public void draw(Graphics2D g2) {
        AffineTransform old = g2.getTransform();
        g2.translate(getX(), getY());
        AffineTransform tran = new AffineTransform();
        tran.rotate(Math.toRadians(getAngle() + 45), SIZE / 2, SIZE / 2);
        g2.drawImage(getImage(), tran, null);
        g2.setTransform(old);
    }

    /**
     * @return the rotated shape used for collision detection
     */
    @Override
    public Shape getShape() {
        AffineTransform af = new AffineTransform();
        af.translate(getX(), getY());
        af.rotate(Math.toRadians(getAngle()), SIZE / 2, SIZE / 2);
        return af.createTransformedShape(getShapeArea());
    }

    /**
     * Checks if the enemy has gone out of the screen boundaries.
     *
     * @param width  the width of the game screen
     * @param height the height of the game screen
     * @return true if enemy is outside visible area and should be removed
     */
    @Override
    public boolean check(int width, int height) {
        Rectangle size = getShape().getBounds();
        return getX() <= -size.getWidth() || getY() < -size.getHeight() || getX() > width || getY() > height;
    }
}