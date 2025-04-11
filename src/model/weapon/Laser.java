package model.weapon;

import strategy.check.TimedAttempt;
import strategy.damage.SimpleDamage;
import strategy.draw.LaserDrawStrategy;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Laser represents a thin, long-range beam that deals instant damage across a line.
 * It is very short-lived and uses a custom visual and collision beam.
 *
 * <p>Laser is ideal for hitting multiple enemies at once.
 * Uses:
 * <ul>
 *     <li>SimpleDamage strategy</li>
 *     <li>Timed lifetime check</li>
 *     <li>Custom draw logic using a rectangle beam</li>
 * </ul>
 */
public class Laser extends AbstractWeapon implements Weapon {

    private final Shape shape;
    private int lifeTime = 3;
    private final double length = 1000;

    /**
     * Constructs a Laser from a given point and angle.
     *
     * @param x     the starting x-position
     * @param y     the starting y-position
     * @param angle direction of the laser in degrees
     */
    public Laser(double x, double y, float angle) {
        super(x, y, angle);
        setSpeed(0);
        setSize(4);
        setDamage(80);
        setColor(new Color(0, 255, 255));
        setCheck(new TimedAttempt(60));
        setDrawStrategy(new LaserDrawStrategy());
        setDamageStrategy(new SimpleDamage());

        double endX = x + Math.cos(Math.toRadians(angle)) * length;
        double endY = y + Math.sin(Math.toRadians(angle)) * length;

        this.shape = new Line2D.Double(x, y, endX, endY);
    }

    /**
     * Updates the internal life counter of the laser.
     */
    @Override
    public void update() {
        lifeTime--;
    }

    /**
     * Returns the transformed shape of the laser for collision checks.
     */
    @Override
    public Shape getShape() {
        double width = getSize();
        Rectangle2D beam = new Rectangle2D.Double(0, -width / 2, length, width);
        AffineTransform af = new AffineTransform();
        af.translate(getX(), getY());
        af.rotate(Math.toRadians(getAngle()));
        return af.createTransformedShape(beam);
    }

    /**
     * Custom drawing logic for the laser beam.
     */
    @Override
    public void draw(Graphics2D g2) {
        AffineTransform old = g2.getTransform();
        g2.setColor(getColor());
        g2.translate(getX(), getY());
        g2.rotate(Math.toRadians(getAngle()));

        Shape beam = new Rectangle2D.Double(0, -getSize() / 2, length, getSize());
        g2.fill(beam);
        g2.setTransform(old);
    }
}