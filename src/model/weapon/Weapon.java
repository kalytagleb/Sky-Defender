package model.weapon;

import java.awt.*;

/**
 * Interface for weapons in the game.
 */

public interface Weapon {
    /**
     * Checks if the weapon is out of bounds or should be removed.
     *
     * @param width the screen width
     * @param height the screen height
     * @return true if the weapon should remain, false if it should be removed
     */
    boolean check(int width, int height);

    /**
     * Gets the shape of the weapon for collision detection.
     *
     * @return the shape
     */
    Shape getShape();
}