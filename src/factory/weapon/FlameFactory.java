package factory.weapon;

import model.weapon.Flame;
import model.weapon.Weapon;

/**
 * Factory for creating Flame weapons
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
