package factory.weapon;

import model.weapon.Weapon;

/**
 * Interface for creating weapon instances.
 */
public interface WeaponFactory {
    /**
     * Creates a new instance of a weapon at the specified coordinates and direction.
     *
     * @param x     x-coordinate of weapon spawn
     * @param y     y-coordinate of weapon spawn
     * @param angle direction angle in degrees
     * @return the created {@link Weapon} instance
     */
    Weapon create(double x, double y, float angle);
}