package model.weapon.factory;

import model.weapon.Weapon;

/**
 * WeaponFactory is an interface representing the Factory Method design pattern
 * for creating different types of weapons.
 *
 * <p>Each implementation of this interface is responsible for instantiating
 * a specific subclass of {@link Weapon}.
 *
 * <p>This allows switching weapon types dynamically at runtime, depending on the
 * selected factory (e.g., rocket, flame, laser).
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