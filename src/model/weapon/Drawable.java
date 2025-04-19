package model.weapon;

import java.awt.*;

/**
 * Interface for drawable game objects.
 */
public interface Drawable {
    /**
     * Draws the object using the specified graphics context.
     *
     * @param g2 the graphics context
     */
    void draw(Graphics2D g2);
}