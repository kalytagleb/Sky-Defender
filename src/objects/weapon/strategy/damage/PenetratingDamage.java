package objects.weapon.strategy.damage;

import objects.enemies.BasicEnemy;
import objects.weapon.AbstractWeapon;

/**
 * PenetratingDamage allows the weapon to damage multiple enemies,
 * e.g., used by area or flame-type weapons.
 */
public class PenetratingDamage implements DamageStrategy {

    /**
     * Applies penetrating damage to the enemy from the source weapon.
     *
     * @param enemy  the enemy to damage
     * @param source the weapon causing the hit
     */
    @Override
    public void applyDamage(BasicEnemy enemy, AbstractWeapon source) {
        enemy.takeDamage(source.getDamage());
    }
}
