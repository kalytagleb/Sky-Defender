package service;

import logic_units.Collision;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Laser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class CollisionService {
    public void check(
            List<AbstractWeapon> weapons,
            List<AbstractEnemy> enemies,
            Runnable onScore,
            Predicate<AbstractWeapon> shouldRemoveWeapon
    ) {
        List<AbstractWeapon> toRemoveWeapons = new ArrayList<>();
        List<AbstractEnemy> toRemoveEnemies = new ArrayList<>();

        for (AbstractWeapon weapon : weapons) {
            for (AbstractEnemy enemy : enemies) {
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