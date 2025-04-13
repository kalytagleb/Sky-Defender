package test.model.enemies;

import model.enemies.FastEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FastEnemyTest {
    private FastEnemy enemy;

    @BeforeEach
    void setUp() {
        enemy = new FastEnemy(100, 100);
    }

    @Test
    void enemyInitializesCorrectly() {
        assertEquals(100, enemy.getX());
        assertEquals(100, enemy.getY());
        assertEquals(1.5f, enemy.getSpeed());
        assertEquals(60, enemy.getHp());
    }

    @Test
    void enemyMovesWhileUpdated() {
        enemy.setAngle(0);
        enemy.setSpeed(1.5f);

        double initialX = enemy.getX();
        enemy.update();

        assertTrue(enemy.getX() > initialX);
    }

    @Test
    void enemyTakesDamage() {
        int initialHp = enemy.getHp();
        enemy.takeDamage(15);
        assertEquals(initialHp - 15, enemy.getHp());
    }

    @Test
    void enemyDiesWhenHpZero() {
        enemy.takeDamage(enemy.getHp());
        assertTrue(enemy.isDead());
    }

    @Test
    void getShapeShouldReturnShape() {
        Shape shape = enemy.getShape();
        assertNotNull(shape);
        assertTrue(shape.getBounds().getWidth() > 0);
        assertTrue(shape.getBounds().getHeight() > 0);
    }
}