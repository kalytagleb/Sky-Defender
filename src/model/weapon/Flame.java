package model.weapon;

import strategy.check.TimedAttempt;
import strategy.damage.PenetratingDamage;
import strategy.draw.FlameDrawStrategy;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Represents a flame weapon with a large area of effect.
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
