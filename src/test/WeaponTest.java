package test;

import model.weapon.Flame;
import model.weapon.Laser;
import model.weapon.Rocket;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeaponTest {
    @Test
    public void testRocketDefaults() {
        Rocket rocket = new Rocket(100, 200, 90);
        assertEquals(2.5f, rocket.getSpeed(), 0.01);
        assertEquals(20, rocket.getSize(), 0.01);
        assertEquals(100, rocket.getDamage());
        assertEquals(new Color(255, 100, 50), rocket.getColor());
        assertTrue(rocket.getShape().getBounds().getWidth() > 0);
        assertTrue(rocket.getShape().getBounds().getHeight() > 0);
    }

    @Test
    public void testFlameProperties() {
        Flame flame = new Flame(50, 50, 0);
        assertEquals(0, flame.getSpeed(), 0.01);
        assertEquals(300, flame.getSize(), 0.01);
        assertEquals(50, flame.getDamage());
        assertEquals(new Color(255, 80, 0), flame.getColor());
    }

    @Test
    public void testLaserDefaults() {
        Laser laser = new Laser(10, 20, 45);
        assertEquals(0, laser.getSpeed(), 0.01);
        assertEquals(4, laser.getSize(), 0.01);
        assertEquals(80, laser.getDamage());
        assertEquals(new Color(0, 255, 255), laser.getColor());
    }

    @Test
    public void testRocketUpdate() {
        Rocket rocket = new Rocket(0, 0, 0);
        double originalX = rocket.getX();
        rocket.update();
        assertTrue(rocket.getX() > originalX);
    }

    @Test
    public void testFlameLifetime() {
        Flame flame = new Flame(0, 0, 0);
        for (int i = 0; i < 60; i++) flame.update();
        assertTrue(flame.check(1000, 1000));
    }

    @Test
    public void testLaserLifetime() {
        Laser laser = new Laser(0, 0, 0);
        for (int i = 0; i < 3; i++) laser.update();
        assertTrue(laser.check(1000, 1000));
    }
}