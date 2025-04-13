package test.model.weapon;

import model.enemies.BasicEnemy;
import model.weapon.Laser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LaserTest {
    private Laser laser;

    @BeforeEach
    void setUp() {
        laser = new Laser(100, 100, 0f);
    }

    @Test
    void laserInitializesCorrectly() {
        assertEquals(0.5f, laser.getSpeed());
        assertEquals(4, laser.getSize());
        assertEquals(80, laser.getDamage());
        assertEquals(new Color(0, 255, 255), laser.getColor());
        assertNotNull(laser.getShape());
        assertNotNull(laser.getDrawStrategy());
        assertNotNull(laser.getDamageStrategy());
        assertNotNull(laser.getCheckStrategy());
    }

    @Test
    void laserExpiresAfter60Updates() {
        for (int i=0; i<59; i++) {
            assertTrue(laser.check(800, 600), "Laser should still be active");
            laser.update();
        }

        laser.update();
        assertFalse(laser.check(800, 600), "Laser should expire after 60 updates");
    }

    @Test
    void getShapeReturnsValidTransformedShape() {
        Shape shape = laser.getShape();
        assertNotNull(shape);
        assertTrue(shape.getBounds().getWidth() > 0);
        assertTrue(shape.getBounds().getHeight() > 0);
    }

    @Test
    void laserDealsDamageToEnemy() {
        BasicEnemy enemy = new BasicEnemy(100, 100);
        int originalHp = enemy.getHp();
        laser.hit(enemy);
        assertTrue(enemy.getHp() < originalHp, "Laser should deal damage to the enemy");
    }

    @Test
    void laserRejectsNegativeSizeAndDamage() {
        laser.setSize(-100);
        laser.setDamage(-50);
        assertEquals(0, laser.getSize());
        assertEquals(0, laser.getDamage());
    }
}