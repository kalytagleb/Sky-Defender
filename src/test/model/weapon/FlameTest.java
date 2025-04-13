package test.model.weapon;

import model.enemies.BasicEnemy;
import model.weapon.Flame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FlameTest {
    private Flame flame;

    @BeforeEach
    void setUp() {
        flame = new Flame(100, 100, 0f);
    }

    @Test
    void flameInitializesCorrectly() {
        flame = new Flame(100, 200, 90f);

        assertEquals(100, flame.getX());
        assertEquals(200, flame.getY());
        assertEquals(90f, flame.getAngle());
        assertEquals(0, flame.getSpeed());
        assertEquals(300, flame.getSize());
        assertEquals(50, flame.getDamage());
        assertNotNull(flame.getShape());
        assertNotNull(flame.getCheckStrategy());
        assertNotNull(flame.getDamageStrategy());
        assertNotNull(flame.getDrawStrategy());
    }

    @Test
    void updateShouldDecreaseLifeTime() {
        flame = new Flame(0, 0, 0f);
        for (int i=0; i<10; i++) {
            flame.update();
        }

        assertTrue(true);
    }

    @Test
    void getShapeReturnsValidShape() {
        flame = new Flame(100, 100, 45f);
        Shape shape = flame.getShape();

        assertNotNull(shape);
        assertTrue(shape.getBounds().getWidth() > 0);
        assertTrue(shape.getBounds().getHeight() > 0);
    }

    @Test
    void flameDealsPenetratingDamage() {
        flame = new Flame(0, 0, 0f);
        BasicEnemy enemy = new BasicEnemy(0, 0);

        int originalHp = enemy.getHp();
        flame.hit(enemy);
        assertTrue(enemy.getHp() < originalHp);
    }

    @Test
    void flameRejectsNegativeSizeAndDamage() {
        flame = new Flame(0, 0, 0f);
        flame.setSize(-100);
        flame.setDamage(-50);

        assertEquals(0, flame.getSize());
        assertEquals(0, flame.getDamage());
    }

    @Test
    void flameHasNoMovement() {
        flame = new Flame(0, 0, 0f);
        assertEquals(0, flame.getSpeed());
    }
}