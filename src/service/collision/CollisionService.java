package service.collision;

import logic_units.Collision;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Manages collisions between weapons and enemies.
 */
public class CollisionService {

    /**
     * Checks collisions between weapons and enemies, applying damage and removing objects as needed.
     *
     * @param weapons the list of weapons
     * @param enemies the list of enemies
     * @param onScore the action to perform when an enemy is destroyed
     * @param shouldRemoveWeapon the condition for removing a weapon
     */
    public <W extends AbstractWeapon, E extends AbstractEnemy> void check(
            List<W> weapons,
            List<E> enemies,
            Runnable onScore,
            Predicate<W> shouldRemoveWeapon
    ) {
        List<W> toRemoveWeapons = new ArrayList<>();
        List<E> toRemoveEnemies = new ArrayList<>();

        for (W weapon : weapons) {
            for (E enemy : enemies) {
                if (Collision.intersects(weapon, enemy)) {
                    weapon.hit(enemy);
                    if (enemy.isDead()) {
                        toRemoveEnemies.add(enemy);
                        onScore.run();
                    }
                    if (shouldRemoveWeapon.test(weapon)) {
                        toRemoveWeapons.add(weapon);
                    }
                    break;
                }
            }
        }

        enemies.removeAll(toRemoveEnemies);
        weapons.removeAll(toRemoveWeapons);
    }
}