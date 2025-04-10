package model.weapon.strategy.check;

import model.GameObject;
import model.weapon.AbstractWeapon;

/**
 * TimedAttempt is a {@link CheckStrategy} that removes a weapon
 * after a certain number of frames have passed.
 */
public class TimedAttempt implements CheckStrategy {

    private int lifetime;

    /**
     * Constructs a TimedAttempt strategy with a given lifetime in frames.
     *
     * @param frames the number of frames before removal
     */
    public TimedAttempt(int frames) {
        this.lifetime = frames;
    }

    /**
     * Decreases lifetime and checks if time has expired.
     *
     * @param weapon   the game object
     * @param width screen width
     * @param height screen height
     * @return true if the object should be removed
     */
    @Override
    public boolean shouldAttempt(AbstractWeapon weapon, int width, int height) {
        lifetime--;
        return lifetime <= 0;
    }
}
