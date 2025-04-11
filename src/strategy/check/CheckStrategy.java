package strategy.check;

import model.weapon.AbstractWeapon;

/**
 * CheckStrategy determines whether a weapon should be removed from the game.
 *
 * <p>It is used to support various lifetime behaviors like timed destruction
 * or going off-screen.
 */
public interface CheckStrategy {

    /**
     * Checks if a weapon should be removed from play.
     *
     * @param weapon   the weapon to evaluate
     * @param width the game screen width
     * @param height the game screen height
     * @return true if the weapon should be removed
     */
    boolean shouldAttempt(AbstractWeapon weapon, int width, int height);
}
