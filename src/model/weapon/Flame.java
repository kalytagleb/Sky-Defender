package model.weapon;

import strategy.check.TimedAttempt;
import strategy.damage.PenetratingDamage;
import strategy.draw.FlameDrawStrategy;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Flame is a short-lived, large-area weapon that deals damage in a wide circular range.
 * It does not move, but persists for a short time before disappearing.
 *
 * <p>It uses:
 * <ul>
 *     <li>Penetrating damage strategy</li>
 *     <li>Timed check to control lifetime</li>
 *     <li>Custom flame draw strategy for dynamic visuals</li>
 * </ul>
 */
public class Flame extends AbstractWeapon implements Weapon {

    private final Shape shape;
    private int lifeTime = 60;

    /**
     * Constructs a Flame at the given position and direction.
     *
     * @param x     the x-coordinate of the flame
     * @param y     the y-coordinate of the flame
     * @param angle the direction the flame is facing
     */
    public Flame(double x, double y, float angle) {
        super(x, y, angle);
        setSpeed(0);
        setSize(300);
        setDamage(50);
        setColor(new Color(255, 80, 0));
        setCheck(new TimedAttempt(60));
        setDamageStrategy(new PenetratingDamage());
        setDrawStrategy(new FlameDrawStrategy());
        this.shape = new Ellipse2D.Double(0, 0, getSize(), getSize());
    }

    /**
     * Updates the flame's internal life counter.
     */
    @Override
    public void update() {
        lifeTime--;
    }

    /**
     * Returns the transformed shape of the flame for rendering and collision.
     */
    @Override
    public Shape getShape() {
        AffineTransform af = new AffineTransform();
        af.translate(getX(), getY());
        af.rotate(Math.toRadians(getAngle()), getSize() / 2, getSize() / 2);
        return af.createTransformedShape(shape);
    }
}
