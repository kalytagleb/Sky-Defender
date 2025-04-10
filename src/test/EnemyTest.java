package test;

import model.enemies.BasicEnemy;
import model.enemies.FastEnemy;
import model.enemies.TankEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTest {
    private BasicEnemy basicEnemy;
    private FastEnemy fastEnemy;
    private TankEnemy tankEnemy;

    @BeforeEach
    public void setUp() {
        basicEnemy = new BasicEnemy(100, 100);
        fastEnemy = new FastEnemy(100, 100);
        tankEnemy = new TankEnemy(100, 100);
    }

    @Test
    public void testBasicEnemyMovement() {
        double xBefore = basicEnemy.getX();
        double yBefore = basicEnemy.getY();
        basicEnemy.setAngle(0);
        basicEnemy.update();
        assertTrue(basicEnemy.getX() > xBefore);
        assertEquals(yBefore, basicEnemy.getY(), 0.001);
    }

    @Test
    public void testFastEnemySpeedIsHigher() {
        assertTrue(fastEnemy.getSpeed() > basicEnemy.getSpeed());
    }

    @Test
    public void testTankEnemyHP() {
        assertEquals(300, tankEnemy.getHp());
        tankEnemy.takeDamage(100);
        assertEquals(200, tankEnemy.getHp());
        tankEnemy.takeDamage(300);
        assertTrue(tankEnemy.isDead());
    }

    @Test
    public void testEnemyTakeDamage() {
        basicEnemy.takeDamage(20);
        assertEquals(80, basicEnemy.getHp());
    }

    @Test
    public void testEnemyIsDead() {
        basicEnemy.takeDamage(100);
        assertTrue(basicEnemy.isDead());
    }
}