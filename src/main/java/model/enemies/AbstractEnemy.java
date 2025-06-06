package model.enemies;

import model.GameObject;

import java.awt.*;
import java.awt.geom.Area;

/**
 * Abstract base class for enemies in the game.
 */
public abstract class AbstractEnemy extends GameObject {

    private float speed;
    private Image image;
    private Area shape;
    private int hp;

    /**
     * Constructs an enemy at a specified location.
     *
     * @param x initial X position
     * @param y initial Y position
     */
    public AbstractEnemy(double x, double y) {
        super(x, y);
    }

    /**
     * Updates the enemy's state, such as movement.
     */
    @Override
    public abstract void update();

    /**
     * Checks whether the enemy is out of bounds and should be removed.
     *
     * @param width  width of the game field
     * @param height height of the game field
     * @return true if enemy is outside bounds and should be removed
     */
    public abstract boolean check(int width, int height);

    /**
     * Gets the movement speed of the enemy.
     *
     * @return current movement speed of the enemy
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets movement speed of the enemy.
     *
     * @param speed new speed value
     */
    public void setSpeed(float speed) {
        this.speed = Math.max(0, speed);
    }

    /**
     * Gets the enemy's sprite image.
     *
     * @return image used to draw the enemy
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the enemy sprite.
     *
     * @param image image to use
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return current shape area used for collision detection
     */
    public Area getShapeArea() {
        return shape;
    }

    /**
     * Sets the shape used for collision.
     *
     * @param shape shape of the enemy
     */
    public void setShapeArea(Area shape) {
        this.shape = shape;
    }

    /**
     * @return current health points (HP) of the enemy
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets current health points.
     *
     * @param hp new HP value
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Reduces HP by given damage amount.
     *
     * @param damage damage to apply
     */
    public void takeDamage(int damage) {
        hp -= damage;
    }

    /**
     * @return true if enemy HP has dropped to zero or below
     */
    public boolean isDead() {
        return hp <= 0;
    }
}