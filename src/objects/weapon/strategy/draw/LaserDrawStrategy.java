package objects.weapon.strategy.draw;

import objects.GameObject;
import objects.weapon.AbstractWeapon;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * LaserDrawStrategy renders a weapon as a horizontal laser beam using a filled rectangle.
 */
public class LaserDrawStrategy implements DrawStrategy {

    /**
     * Draws a laser beam from the weapon’s position in the direction of its angle.
     *
     * @param g2  the graphics context
     * @param obj the laser weapon to draw
     */
    @Override
    public void draw(Graphics2D g2, GameObject obj) {
        if (!(obj instanceof AbstractWeapon weapon)) return;

        double width = weapon.getSize();
        double length = 120;

        AffineTransform old = g2.getTransform();
        g2.translate(weapon.getX(), weapon.getY());
        g2.rotate(Math.toRadians(weapon.getAngle()));

        g2.setColor(weapon.getColor());
        Shape beam = new Rectangle2D.Double(0, -width / 2, length, width);
        g2.fill(beam);

        g2.setTransform(old);
    }
}
