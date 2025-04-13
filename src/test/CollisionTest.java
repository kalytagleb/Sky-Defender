package test;

import logic_units.Collision;
import model.Player;
import model.enemies.BasicEnemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {

    @Test
    public void testCollisionDetected() {
        Player player = new Player();
        player.changeLocation(100, 100);

        BasicEnemy enemy = new BasicEnemy(100, 100);

        assertTrue(Collision.intersects(player, enemy));
    }

    @Test
    public void testNoCollision() {
        Player player = new Player();
        player.changeLocation(100, 100);

        BasicEnemy enemy = new BasicEnemy(1000, 1000);

        assertFalse(Collision.intersects(player, enemy));
    }

    @Test
    public void testNearMissCollision() {
        Player player = new Player();
        player.changeLocation(100, 100);

        BasicEnemy enemy = new BasicEnemy(100 + Player.PLAYER_SIZE + 1, 100);

        assertFalse(Collision.intersects(player, enemy));
    }
}
