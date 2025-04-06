package objects.weapon.strategy.damage;

import objects.enemies.BasicEnemy;
import objects.weapon.AbstractWeapon;

/**
 * SimpleDamage is a standard {@link DamageStrategy} that applies
 * the weapon’s full damage to the enemy.
 */
public class SimpleDamage implements DamageStrategy {

    /**
     * Reduces the enemy’s HP by the weapon’s damage amount.
     *
     * @param enemy  the enemy being hit
     * @param source the weapon causing the hit
     */
    @Override
    public void applyDamage(BasicEnemy enemy, AbstractWeapon source) {
        enemy.takeDamage(source.getDamage());
    }
}
