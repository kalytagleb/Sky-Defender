package factory.weapon;

import model.weapon.Laser;
import model.weapon.Weapon;

/**
 * LaserFactory is a concrete implementation of {@link WeaponFactory}
 * responsible for creating {@link Laser} instances.
 */
public class LaserFactory implements WeaponFactory {

    /**
     * Creates a new Laser weapon.
     *
     * @param x     x-coordinate of spawn
     * @param y     y-coordinate of spawn
     * @param angle direction angle
     * @return new {@link Laser} instance
     */
    @Override
    public Weapon create(double x, double y, float angle) {
        return new Laser(x, y, angle);
    }
}