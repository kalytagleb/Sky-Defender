package model;

import input.Key;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private Key key;

    @BeforeEach
    void setUp() {
        player = new Player();
        key = new Key();
        player.setKey(key);
    }

    @Test
    void initialPositionIsZero() {
        assertEquals(0.0, player.getX());
        assertEquals(0.0, player.getY());
    }

    @Test
    void rotatesLeftWhenLeftKeyIsPressed() {
        key.setKey_left(true);
        player.setKey(key);

        float originalAngle = player.getAngle();
        player.update();

        float newAngle = player.getAngle();
        boolean rotatedLeft = newAngle < originalAngle || (originalAngle < 2.5f && newAngle > 360 - 2.5f);

        assertTrue(rotatedLeft, "Player should rotate left");
    }

    @Test
    void rotatesRightWhenRightKeyIsPressed() {
        key.setKey_right(true);
        player.setKey(key);

        float originalAngle = player.getAngle();
        player.update();

        float newAngle = player.getAngle();
        boolean rotatedRight = newAngle > originalAngle || (originalAngle > 360 - 2.5f && newAngle < 2.5f);

        assertTrue(rotatedRight, "Player should rotate right");
    }

    @Test
    void acceleratesWhenSpacePressed() {
        key.setKey_space(true);
        player.setKey(key);

        double beforeX = player.getX();
        double beforeY = player.getY();

        player.update();

        boolean moved = beforeX != player.getX() || beforeY != player.getY();
        assertTrue(moved, "Player should move after pressing space");
    }

    @Test
    void slowsDownWhenNoKeyPressed() {
        key.setKey_space(true);
        player.setKey(key);

        // Player accelerating
        for (int i=0; i<10; i++) {
            player.update();
        }

        double prevX = player.getX();
        double prevY = player.getY();

        key.setKey_space(false);

        for (int i=0; i<100; i++) {
            player.update();
        }

        double afterX = player.getX();
        double afterY = player.getY();

        player.update();

        assertEquals(afterX, player.getX(), 0.1);
        assertEquals(afterY, player.getY(), 0.1);
    }

    @Test
    void takesDamageCorrectly() {
        player.takeDamage(40);
        assertEquals(60, player.getHp());
    }

    @Test
    void diesWhenHpZero() {
        player.takeDamage(100);
        assertTrue(player.isDead());
    }

    @Test
    void playerIsAliveWithPositiveHp() {
        player.takeDamage(10);
        assertFalse(player.isDead());
    }

    @Test
    void returnsCorrectCenterCoordinates() {
        player.changeLocation(100, 200);

        assertEquals(100 + Player.PLAYER_SIZE / 2, player.getCenterX());
        assertEquals(200 + Player.PLAYER_SIZE / 2, player.getCenterY());
    }

    @Test
    void getShapeReturnsNonNullShape() {
        assertNotNull(player.getShape());
    }

    @Test
    void setsPlayerHpCorrectly() {
        player.setHp(73);
        assertEquals(73, player.getHp());
    }

    @Test
    void returnsCurrentSpeed() {
        player.setKey(key);
        key.setKey_space(true);
        player.update();
        assertTrue(player.getSpeed() > 0);
    }
}