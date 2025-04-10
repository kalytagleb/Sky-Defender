package service.collision;

import logic_units.Collision;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Laser;

import java.util.Iterator;
import java.util.List;

public class CollisionService {
    public void checkCollisions(List<AbstractWeapon> weapons, List<AbstractEnemy> enemies) {
        Iterator<AbstractWeapon> weaponIterator = weapons.iterator();

        while (weaponIterator.hasNext()) {
            AbstractWeapon weapon = weaponIterator.next();

            for (AbstractEnemy enemy : enemies) {
                if (Collision.intersects(weapon, enemy)) {
                    weapon.hit(enemy);
                    if (enemy.isDead()) enemies.remove(enemy);
                    if (!(weapon instanceof Laser)) weapons.remove(weapon);
                    break;
                }
            }
        }
    }
}