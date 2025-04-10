package model.weapon.strategy.damage;

import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.weapon.AbstractWeapon;

/**
 * SimpleDamage is a standard {@link DamageStrategy} that applies
 * the weapon’s full damage to the enemy.
 */
public class SimpleDamage implements DamageStrategy {

    /**
     * Reduces the enemy’s HP by the weapon’s damage amount.
     *
     * @param enemy  the enemy being hit
     * @param weapon the weapon causing the hit
     */
    @Override
    public void applyDamage(AbstractEnemy enemy, AbstractWeapon weapon) {
        enemy.takeDamage(weapon.getDamage());
    }
}
