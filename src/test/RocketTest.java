package test;

import model.enemies.BasicEnemy;
import model.weapon.Rocket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RocketTest {

    @Test
    void weaponsMovesInDirectionWhenUpdated() {
        Rocket rocket = new Rocket(0, 0, 0f);
        double initialX = rocket.getX();

        rocket.update();

        assertTrue(rocket.getX() > initialX, "Rocket should move along the X axis");
    }

    @Test
    void weaponIsRemovedWhenOutOfBounds() {
        Rocket rocket = new Rocket(2000, 2000, 0f);
        boolean isStillValid = rocket.check(800, 600);
        assertFalse(isStillValid, "Rocket should be removed when out of bounds");
    }

    @Test
    void weaponDealsDamageToEnemy() {
        Rocket rocket = new Rocket(0, 0, 0f);
        BasicEnemy enemy = new BasicEnemy(0, 0);

        int initialHp = enemy.getHp();
        rocket.hit(enemy);

        assertTrue(enemy.getHp() < initialHp, "Rocket should decrease enemy HP");
    }

    @Test
    void weaponRejectsNegativeDamage() {
        Rocket rocket = new Rocket(0, 0, 0f);
        rocket.setDamage(-50);

        assertEquals(0, rocket.getDamage(), "Damage should not be negative");
    }

    @Test
    void weaponRejectsNegativeSizeAndSpeed() {
        Rocket rocket = new Rocket(0, 0, 0f);
        rocket.setSize(-100);
        rocket.setSpeed(-5);

        assertEquals(0, rocket.getSize(), "Size should be non-negative");
        assertEquals(0, rocket.getSpeed(), "Speed should be non-negative");
    }
}