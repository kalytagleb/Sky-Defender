package model.enemies;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 * Abstract base class for polygon-shaped enemies.
 */
public class AbstractPolygonEnemy extends AbstractEnemy {

    private final double size;

    /**
     * Constructs a polygon enemy with the specified attributes.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param size the size of the enemy
     * @param hp the health points
     * @param speed the movement speed
     * @param imagePath the path to the sprite image
     * @param shape the collision shape
     */
    public AbstractPolygonEnemy(double x, double y, double size, int hp, float speed, String imagePath, Area shape) {
        super(x, y);
        this.size = size;
        setHp(hp);
        setSpeed(speed);
        setImage(new ImageIcon(getClass().getResource(imagePath)).getImage());
        setShapeArea(shape);
    }

    @Override
    public void update() {
        double dx = Math.cos(Math.toRadians(getAngle())) * getSpeed();
        double dy = Math.sin(Math.toRadians(getAngle())) * getSpeed();
        setX(getX() + dx);
        setY(getY() + dy);
    }

    @Override
    public void draw(Graphics2D g2) {
        AffineTransform old = g2.getTransform();
        g2.translate(getX(), getY());
        g2.rotate(Math.toRadians(getAngle()+45), size / 2, size / 2);
        g2.drawImage(getImage(), 0, 0, (int) size, (int) size,null);
        g2.setTransform(old);
    }

    @Override
    public Shape getShape() {
        AffineTransform af = new AffineTransform();
        af.translate(getX(), getY());
        af.rotate(Math.toRadians(getAngle()), size / 2, size / 2);
        return af.createTransformedShape(getShapeArea());
    }

    @Override
    public boolean check(int width, int height) {
        Rectangle size = getShape().getBounds();
        return getX() <= -size.getWidth() || getY() < -size.getHeight() || getX() > width || getY() > height;
    }

    /**
     * Gets the size of the enemy.
     *
     * @return the size
     */
    public double getSize() {
        return size;
    }
}