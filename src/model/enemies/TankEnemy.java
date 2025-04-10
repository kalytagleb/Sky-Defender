package model.enemies;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * TankEnemy is a subclass of {@link BasicEnemy} that moves slowly but has significantly more health.
 * <p>
 * This enemy type is designed to absorb more hits, making it a tank-like unit on the battlefield.
 * It uses a custom image and the same collision shape as {@link BasicEnemy}.
 */
public class TankEnemy extends BasicEnemy {

    /**
     * Size of the tank enemy used for drawing and collision bounds.
     */
    public static final double SIZE = 50;

    /**
     * Constructs a new TankEnemy at the specified position.
     * Sets high HP and slower speed to reflect tank-like behavior.
     *
     * @param x initial X coordinate
     * @param y initial Y coordinate
     */
    public TankEnemy(double x, double y) {
        super(x, y);
        setSpeed(0.15f); // Slower than other enemies
        setHp(300);      // Much higher health
        setImage(new ImageIcon(getClass().getResource("/images/TankEnemy.png")).getImage());

        // Shape inherited from BasicEnemy
    }

    /**
     * Draws the TankEnemy on screen using its image.
     *
     * @param g2 the graphics context used for drawing
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
