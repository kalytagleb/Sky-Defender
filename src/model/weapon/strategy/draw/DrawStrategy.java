package model.weapon.strategy.draw;

import model.GameObject;
import model.weapon.AbstractWeapon;

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
     * @param weapon the game object to draw
     */
    void draw(Graphics2D g2, AbstractWeapon weapon);
}
