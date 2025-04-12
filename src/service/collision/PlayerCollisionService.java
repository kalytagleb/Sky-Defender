package service.collision;

import logic_units.Collision;
import model.Player;
import model.enemies.AbstractEnemy;

import java.util.Iterator;
import java.util.List;

public class PlayerCollisionService {
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