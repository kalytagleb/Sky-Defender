package test;

import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
        player.changeLocation(100, 100);
        player.setHp(100);
    }

    @Test
    public void testInitialHP() {
        assertEquals(100, player.getHp(), "Initial HP should be 100");
    }

    @Test
    public void testTakeDamage() {
        player.takeDamage(30);
        assertEquals(70, player.getHp(), "HP should be reduced to 70 after taking 30 damage");
    }

    @Test
    public void testIsDeadFalse() {
        player.takeDamage(99);
        assertFalse(player.isDead(), "Player should not be dead with 1 HP left");
    }

    @Test
    public void testIsDeadTrue() {
        player.takeDamage(100);
        assertTrue(player.isDead(), "Player should be dead when HP reaches 0");
    }

    @Test
    public void testUpdateMovement() {
        player.setAngle(0);
        player.speedUp();
        double initialX = player.getX();
        player.update();
        assertTrue(player.getX() > initialX, "Player should move to the right when angle is 0");
    }
}