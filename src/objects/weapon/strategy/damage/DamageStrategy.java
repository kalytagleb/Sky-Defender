package objects.weapon.strategy.damage;

import objects.enemies.BasicEnemy;
import objects.weapon.AbstractWeapon;

/**
 * DamageStrategy defines the behavior for applying damage to an enemy
 * from a weapon hit.
 *
 * <p>This pattern enables customization of how different weapons
 * interact with enemies (e.g., normal hit, piercing, etc.).
 */
public interface DamageStrategy {

    /**
     * Applies damage to the specified enemy based on the weapon's properties.
     *
     * @param enemy  the enemy to damage
     * @param source the source weapon
     */
    void applyDamage(BasicEnemy enemy, AbstractWeapon source);
}