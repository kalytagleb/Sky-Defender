package factory.weapon;

import model.weapon.AbstractWeapon;
import model.weapon.Weapon;

/**
 * Interface for creating weapon instances.
 */
public interface WeaponFactory<T extends AbstractWeapon> {
    /**
     * Creates a new instance of a weapon at the specified coordinates and direction.
     *
     * @param x     x-coordinate of weapon spawn
     * @param y     y-coordinate of weapon spawn
     * @param angle direction angle in degrees
     * @return the created {@link Weapon} instance
     */
    T create(double x, double y, float angle);
}