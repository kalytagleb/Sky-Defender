package model.weapon.strategy.damage;

import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.weapon.AbstractWeapon;

/**
 * PenetratingDamage allows the weapon to damage multiple enemies,
 * e.g., used by area or flame-type weapons.
 */
public class PenetratingDamage implements DamageStrategy {

    /**
     * Applies penetrating damage to the enemy from the source weapon.
     *
     * @param enemy  the enemy to damage
     * @param weapon the weapon causing the hit
     */
    @Override
    public void applyDamage(AbstractEnemy enemy, AbstractWeapon weapon) {
        enemy.takeDamage(weapon.getDamage());
    }
}
