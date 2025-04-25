package strategy.check;

import model.weapon.AbstractWeapon;

/**
 * OutOfBoundsAttempt is a {@link CheckStrategy} that removes a weapon
 * if it travels outside the screen bounds.
 */
public class OutOfBoundsAttempt implements CheckStrategy {

    /**
     * Returns true if the weapon is outside of screen bounds.
     *
     * @param weapon   the game object
     * @param width screen width
     * @param height screen height
     * @return true if the object is out of bounds
     */
    @Override
    public boolean shouldAttempt(AbstractWeapon weapon, int width, int height) {
        double x = weapon.getX();
        double y = weapon.getY();
        double size = weapon.getSize();

        return x < -size || x > width + size || y < -size || y > height + size;
    }
}
