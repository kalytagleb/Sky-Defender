package model.weapon;

import java.awt.*;

/**
 * Interface for all weapon implementations.
 * Requires basic methods for boundary checking and collision shape.
 */

public interface Weapon {
    boolean check(int width, int height);
    Shape getShape();
}