package model.weapon;

import strategy.check.OutOfBoundsAttempt;
import strategy.damage.SimpleDamage;
import strategy.draw.CircleDrawStrategy;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Represents a rocket weapon with high damage.
 */
public class Rocket extends AbstractWeapon {

    private final Shape shape;

    /**
     * Constructs a Rocket at the given position and direction.
     *
     * @param x     the x-coordinate of the starting position
     * @param y     the y-coordinate of the starting position
     * @param angle the direction angle in degrees
     */
    public Rocket(double x, double y, float angle) {
        super(x, y, angle);
        setSpeed(4f);
        setSize(20);
        setDamage(100);
        setColor(new Color(255, 100, 50));
        setCheck(new OutOfBoundsAttempt());
        setDrawStrategy(new CircleDrawStrategy());
        setDamageStrategy(new SimpleDamage());
        this.shape = new Ellipse2D.Double(0, 0, getSize(), getSize());
    }

    /**
     * Returns the shape of the Rocket, transformed by its position and angle.
     */
    @Override
    public Shape getShape() {
        System.out.println("Rocket.getShape() is called");
        Shape baseShape = new Ellipse2D.Double(0, 0, getSize(), getSize());
        AffineTransform af = new AffineTransform();
        af.translate(getX(), getY());
        af.rotate(Math.toRadians(getAngle()), getSize() / 2, getSize() / 2);
        return af.createTransformedShape(baseShape);
    }
}