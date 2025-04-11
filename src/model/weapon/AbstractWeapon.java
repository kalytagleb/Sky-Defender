package model.weapon;

import model.GameObject;
import model.enemies.AbstractEnemy;
import strategy.check.CheckStrategy;
import strategy.damage.DamageStrategy;
import strategy.draw.DrawStrategy;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * AbstractWeapon is an abstract base class for all weapons in the game.
 * It extends {@link GameObject} and provides common fields and logic
 * for projectiles such as speed, damage, color, and collision strategies.
 *
 * <p>This class also supports Strategy design pattern via:
 * <ul>
 *     <li>{@link DrawStrategy} - custom rendering logic</li>
 *     <li>{@link CheckStrategy} - lifespan or boundary logic</li>
 *     <li>{@link DamageStrategy} - damage application behavior</li>
 * </ul>
 *
 * <p>Subclasses of this weapon must implement {@link #getShape()} and can optionally
 * override {@link #update()} or {@link #draw(Graphics2D)} for specific behavior.
 */
public abstract class AbstractWeapon extends GameObject implements Weapon, Updatable, Drawable {

    private float speed;
    private double size;
    private int damage;
    private Color color;

    private CheckStrategy checkStrategy;
    private DrawStrategy drawStrategy;
    private DamageStrategy damageStrategy;

    /**
     * Constructs a new AbstractWeapon at a given position and facing angle.
     *
     * @param x     initial x-coordinate
     * @param y     initial y-coordinate
     * @param angle direction angle in degrees
     */
    public AbstractWeapon(double x, double y, float angle) {
        super(x, y);
        setAngle(angle);
    }

    /**
     * Updates weapon movement based on its speed and direction.
     * Subclasses can override this method if needed.
     */
    @Override
    public void update() {
        double dx = Math.cos(Math.toRadians(getAngle())) * speed;
        double dy = Math.sin(Math.toRadians(getAngle())) * speed;
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * Sets the strategy that determines whether the weapon should still exist.
     *
     * @param strategy check logic (e.g., lifetime, bounds)
     */
    public void setCheck(CheckStrategy strategy) {
        this.checkStrategy = strategy;
    }

    /**
     * Sets the visual rendering strategy for this weapon.
     *
     * @param strategy drawing logic (e.g., beam, circle)
     */
    public void setDrawStrategy(DrawStrategy strategy) {
        this.drawStrategy = strategy;
    }

    /**
     * Sets the strategy that applies damage to enemies.
     *
     * @param strategy damage logic
     */
    public void setDamageStrategy(DamageStrategy strategy) {
        this.damageStrategy = strategy;
    }

    /**
     * Whether the weapon should still be kept on screen.
     *
     * @param width  screen width
     * @param height screen height
     * @return false if the weapon should be removed
     */
    public boolean check(int width, int height) {
        return !checkStrategy.shouldAttempt(this, width, height);
    }

    /**
     * Delegates drawing to the configured DrawStrategy.
     *
     * @param g2 graphics context
     */
    @Override
    public void draw(Graphics2D g2) {
        if (drawStrategy != null) {
            drawStrategy.draw(g2, this);
        }
    }

    @Override
    public Shape getShape() {
        return new Rectangle2D.Double(getX(), getY(), size, size);
    }

    /**
     * Applies damage to a basic enemy using the defined strategy.
     *
     * @param enemy the enemy being hit
     */
    public void hit(AbstractEnemy enemy) {
        if (damageStrategy != null) {
            damageStrategy.applyDamage(enemy, this);
        }
    }

    // Getters and Setters

    public float getSpeed() {
        return speed;
    }

    public double getSize() {
        return size;
    }

    public int getDamage() {
        return damage;
    }

    public Color getColor() {
        return color;
    }

    public void setSpeed(float speed) {
        this.speed = Math.max(0, speed);
    }

    public void setSize(double size) {
        this.size = Math.max(0, size);
    }

    public void setDamage(int damage) {
        this.damage = Math.max(0, damage);
    }

    public void setColor(Color color) {
        if (color != null) {
            this.color = color;
        }
    }

    public CheckStrategy getCheckStrategy() {
        return checkStrategy;
    }

    public DrawStrategy getDrawStrategy() {
        return drawStrategy;
    }

    public DamageStrategy getDamageStrategy() {
        return damageStrategy;
    }
}