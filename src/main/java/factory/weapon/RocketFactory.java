package factory.weapon;

import model.weapon.Rocket;

/**
 * Factory for creating Rocket weapons.
 */
public class RocketFactory implements WeaponFactory<Rocket> {

    /**
     * Creates a new Rocket weapon.
     *
     * @param x     x-coordinate of spawn
     * @param y     y-coordinate of spawn
     * @param angle direction angle
     * @return new {@link Rocket} instance
     */
    @Override
    public Rocket create(double x, double y, float angle) {
        return new Rocket(x, y, angle);
    }
}
