package test.service.collision;

import model.enemies.AbstractEnemy;
import model.enemies.BasicEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Rocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.collision.CollisionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollisionServiceTest {
    private CollisionService collisionService;

    @BeforeEach
    void setUp() {
        collisionService = new CollisionService();
    }

    @Test
    void testEnemyDiesAndRemoved() {
        AbstractEnemy enemy = new BasicEnemy(100, 100);
        enemy.setHp(50);

        AbstractWeapon weapon = new Rocket(100, 100, 0);
        weapon.setDamage(50);

        List<AbstractEnemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        List<AbstractWeapon> weapons = new ArrayList<>();
        weapons.add(weapon);

        int[] score = {0};

        collisionService.check(weapons, enemies, () -> score[0]++, w -> true);

        assertTrue(enemies.isEmpty());
        assertTrue(weapons.isEmpty());
        assertEquals(1, score[0]);
    }

    @Test
    void testEnemySurvivesAndNothingIsRemoved() {
        AbstractEnemy enemy = new BasicEnemy(100, 100);
        enemy.setHp(100);

        AbstractWeapon weapon = new Rocket(100, 100, 0);
        weapon.setDamage(20);

        List<AbstractEnemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        List<AbstractWeapon> weapons = new ArrayList<>();
        weapons.add(weapon);

        collisionService.check(weapons, enemies, () -> {}, w -> false);

        assertEquals(1, enemies.size());
        assertEquals(1, weapons.size());
    }

    @Test
    void testNoCollisionOccurs() {
        AbstractEnemy enemy = new BasicEnemy(500, 500); // Far away
        AbstractWeapon weapon = new Rocket(100, 100, 0);

        List<AbstractEnemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        List<AbstractWeapon> weapons = new ArrayList<>();
        weapons.add(weapon);

        collisionService.check(weapons, enemies, () -> fail("Score should not increase"), w -> true);

        assertEquals(1, enemies.size());
        assertEquals(1, weapons.size());
    }
}