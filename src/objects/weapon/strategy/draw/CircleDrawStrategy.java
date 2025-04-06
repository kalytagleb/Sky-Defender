package objects.weapon.strategy.draw;

import objects.GameObject;
import objects.weapon.AbstractWeapon;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * CircleDrawStrategy is a simple implementation of {@link DrawStrategy}
 * that draws a weapon as a filled circle.
 */
public class CircleDrawStrategy implements DrawStrategy {

    /**
     * Draws a circle using the weapon's color and size at its current position.
     *
     * @param g2  the graphics context
     * @param obj the weapon to draw
     */
    @Override
    public void draw(Graphics2D g2, GameObject obj) {
        if (!(obj instanceof AbstractWeapon weapon)) return;

        AffineTransform old = g2.getTransform();
        g2.setColor(weapon.getColor());
        g2.translate(weapon.getX(), weapon.getY());

        Shape shape = new Ellipse2D.Double(0, 0, weapon.getSize(), weapon.getSize());
        g2.fill(shape);

        g2.setTransform(old);
    }
}
