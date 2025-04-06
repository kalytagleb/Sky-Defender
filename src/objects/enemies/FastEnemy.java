package objects.enemies;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

/**
 * FastEnemy is a subclass of {@link BasicEnemy} that moves faster but has lower health.
 * <p>
 * This type of enemy adds challenge by reaching the player more quickly than regular enemies.
 * It uses a distinct image and retains the same shape as {@link BasicEnemy} for collision.
 */
public class FastEnemy extends BasicEnemy {

    /**
     * Size of the enemy used for rendering and positioning.
     */
    public static final double SIZE = 50;

    /**
     * Constructs a new FastEnemy at the specified coordinates.
     * Sets a lower HP and higher speed compared to BasicEnemy.
     *
     * @param x initial X position
     * @param y initial Y position
     */
    public FastEnemy(double x, double y) {
        super(x, y);
        setHp(50); // Less HP than regular enemy
        setSpeed(0.6f); // Faster than regular enemy
        setImage(new ImageIcon(getClass().getResource("/images/FastEnemy.png")).getImage());

        // Shape inherited from BasicEnemy
    }

    /**
     * Draws the FastEnemy using its unique image.
     *
     * @param g2 the graphics context used for rendering
     */
    @Override
    public void draw(Graphics2D g2) {
        AffineTransform old = g2.getTransform();
        g2.translate(getX(), getY());
        g2.rotate(Math.toRadians(getAngle() + 45), SIZE / 2, SIZE / 2);
        g2.drawImage(getImage(), 0, 0, (int) SIZE, (int) SIZE, null);
        g2.setTransform(old);
    }
}