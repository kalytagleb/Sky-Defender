package model.weapon.factory;

import model.weapon.Flame;
import model.weapon.Weapon;

/**
 * FlameFactory is a concrete implementation of {@link WeaponFactory}
 * responsible for creating {@link Flame} instances.
 */
public class FlameFactory implements WeaponFactory {

    /**
     * Creates a new Flame weapon.
     *
     * @param x     x-coordinate of spawn
     * @param y     y-coordinate of spawn
     * @param angle direction angle
     * @return new {@link Flame} instance
     */
    @Override
    public Weapon create(double x, double y, float angle) {
        return new Flame(x, y, angle);
    }
}
