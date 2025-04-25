package data.weapon;

import java.io.Serializable;

/**
 * Represents the main.core.data for a weapon in the saved game state.
 * Implements Serializable to enable saving to a file.
 */
public class WeaponData implements Serializable {
    private final String type;
    private final double x, y;
    private final float angle;
    private final int damage;

    /**
     * Constructs a WeaponData object with the specified attributes.
     *
     * @param type the type of weapon
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param angle the rotation angle
     * @param damage the damage value
     */
    public WeaponData(String type, double x, double y, float angle, int damage) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.damage = damage;
    }

    /**
     * Gets the weapon type
     *
     * @return the weapon type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the x-coordinate
     *
     * @return the x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate
     *
     * @return the y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the rotation angle.
     *
     * @return the angle
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Gets the damage value.
     *
     * @return the damage value.
     */
    public int getDamage() {
        return damage;
    }
}