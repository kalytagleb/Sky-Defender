package strategy.draw;

import model.weapon.AbstractWeapon;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * FlameDrawStrategy is a custom {@link DrawStrategy} for drawing
 * a flame effect with dynamic flickering and transparency.
 */
public class FlameDrawStrategy implements DrawStrategy {

    private final Random random = new Random();

    /**
     * Draws a flickering flame shape with alpha blending to simulate fire.
     *
     * @param g2  the graphics context
     * @param weapon the flame weapon to draw
     */
    @Override
    public void draw(Graphics2D g2, AbstractWeapon weapon) {
        double baseSize = weapon.getSize();
        double deviation = random.nextDouble() * 10 - 5;
        double size = Math.max(0, baseSize + deviation);

        AffineTransform old = g2.getTransform();
        g2.translate(weapon.getX(), weapon.getY());

        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));

        g2.setColor(weapon.getColor());
        Shape fire = new Ellipse2D.Double(0, 0, size, size);
        g2.fill(fire);

        g2.setComposite(originalComposite);
        g2.setTransform(old);
    }
}
