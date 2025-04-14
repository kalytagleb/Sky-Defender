package test.service.collision;

import model.Player;
import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.collision.PlayerCollisionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerCollisionServiceTest {

    private PlayerCollisionService playerCollisionService;
    private Player player;
    private List<AbstractEnemy> enemies;

    @BeforeEach
    void setUp() {
        playerCollisionService = new PlayerCollisionService();
        player = new Player();
        player.changeLocation(100, 100);
        player.setHp(100);
        enemies = new ArrayList<>();
    }

    @Test
    void testCollisionOccurs() {
        AbstractEnemy enemy = new BasicEnemy(100, 100);
        enemies.add(enemy);

        playerCollisionService.check(player, enemies);

        assertEquals(50, player.getHp(), "Player should take 50 damage");
        assertTrue(enemies.isEmpty(), "Enemy should be removed after collision");
    }

    @Test
    void testNoCollisionOccurs() {
        AbstractEnemy enemy = new BasicEnemy(500, 500);
        enemies.add(enemy);

        playerCollisionService.check(player, enemies);

        assertEquals(100, player.getHp(), "Player should not take damage");
        assertEquals(1, enemies.size(), "Enemy should not be removed");
    }
}