package model;

import java.awt.*;

/**
 * Abstract base class for all game objects.
 */

public abstract class GameObject {
    private double x;
    private double y;
    private float angle;

    /**
     * Constructs a game object at the specified coordinates.
     *
     * @param x initial X position
     * @param y initial Y position
     */
    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the state or position of the object.
     */
    public abstract void update();

    /**
     * Renders the object using the given graphics context.
     *
     * @param g2 the graphics context for drawing
     */
    public abstract void draw(Graphics2D g2);

    /**
     * Returns the object's shape used for collision detection.
     *
     * @return the geometric shape representing this object
     */
    public abstract Shape getShape();

    /**
     * @return current X coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * @return current Y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * @return current angle of rotation in degrees
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Sets a new X coordinate.
     *
     * @param x new X position
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets a new Y coordinate.
     *
     * @param y new Y position
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets a new angle in degrees. Automatically wraps around if out of [0–359].
     *
     * @param angle the angle to set
     */
    public void setAngle(float angle) {
        if (angle < 0) angle = 359;
        else if (angle > 359) angle = 0;
        this.angle = angle;
    }

    /**
     * Changes the object's position.
     *
     * @param x new X position
     * @param y new Y position
     */
    public void changeLocation(double x, double y) {
        setX(x);
        setY(y);
    }
}