package objects.weapon.strategy.draw;

import objects.GameObject;

import java.awt.*;

/**
 * DrawStrategy defines a strategy for rendering game objects.
 *
 * <p>This interface is used by weapons (and potentially other game objects)
 * to abstract away the rendering logic, making it flexible to define different
 * drawing styles for each weapon type.
 */
public interface DrawStrategy {

    /**
     * Draws the specified game object using this strategy.
     *
     * @param g2  the graphics context
     * @param obj the game object to draw
     */
    void draw(Graphics2D g2, GameObject obj);
}
