package objects.weapon.strategy.check;

import objects.GameObject;
import objects.weapon.AbstractWeapon;

/**
 * OutOfBoundsAttempt is a {@link CheckStrategy} that removes a weapon
 * if it travels outside the screen bounds.
 */
public class OutOfBoundsAttempt implements CheckStrategy {

    /**
     * Returns true if the weapon is outside of screen bounds.
     *
     * @param obj   the game object
     * @param width screen width
     * @param height screen height
     * @return true if the object is out of bounds
     */
    @Override
    public boolean shouldAttempt(GameObject obj, int width, int height) {
        double x = obj.getX();
        double y = obj.getY();
        double size = obj instanceof AbstractWeapon w ? w.getSize() : 0;

        return x < -size || x > width || y < -size || y > height;
    }
}
