package factory.weapon;

import model.weapon.Flame;

/**
 * Factory for creating Flame weapons
 */
public class FlameFactory implements WeaponFactory<Flame> {

    /**
     * Creates a new Flame weapon.
     *
     * @param x     x-coordinate of spawn
     * @param y     y-coordinate of spawn
     * @param angle direction angle
     * @return new {@link Flame} instance
     */
    @Override
    public Flame create(double x, double y, float angle) {
        return new Flame(x, y, angle);
    }
}
