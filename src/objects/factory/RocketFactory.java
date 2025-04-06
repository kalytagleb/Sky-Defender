package objects.factory;

import objects.weapon.Rocket;
import objects.weapon.Weapon;

/**
 * RocketFactory is a concrete implementation of {@link WeaponFactory}
 * responsible for creating {@link Rocket} instances.
 */
public class RocketFactory implements WeaponFactory {

    /**
     * Creates a new Rocket weapon.
     *
     * @param x     x-coordinate of spawn
     * @param y     y-coordinate of spawn
     * @param angle direction angle
     * @return new {@link Rocket} instance
     */
    @Override
    public Weapon create(double x, double y, float angle) {
        return new Rocket(x, y, angle);
    }
}
