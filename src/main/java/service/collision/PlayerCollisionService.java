package service.collision;

import logic_units.Collision;
import model.Player;
import model.enemies.AbstractEnemy;

import java.util.List;

/**
 * Manages collisions between the player and enemies.
 */
public class PlayerCollisionService {
    /**
     * Checks collisions between the player and enemies, applying damage to the player.
     *
     * @param player the player object
     * @param enemies the list of enemies
     */
    public void check(Player player, List<AbstractEnemy> enemies) {
        for (AbstractEnemy enemy : enemies) {
            if (Collision.intersects(player, enemy)) {
                player.takeDamage(50);
                enemies.remove(enemy);
                break;
            }
        }
    }
}