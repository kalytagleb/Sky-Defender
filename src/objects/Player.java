package objects;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Represents the player-controlled aircraft in the game.
 * <p>
 * The player can rotate, accelerate forward, and use various weapons.
 * This class handles player movement, drawing, health management, and collision boundaries.
 */

public class Player extends GameObject {
    /**
     * Constant size of the player's plane sprite.
     */
    public static final double PLAYER_SIZE = 64;

    private final Image image;
    private final Image image_move;
    private float speed = 0f;
    private final float MAX_SPEED = 1f;
    private boolean speedUp = false;
    private int hp = 100;

    /**
     * Constructs a new Player object at default coordinates (0, 0).
     * Loads idle and moving sprites.
     */
    public Player() {
        super(0, 0);
        image = new ImageIcon(getClass().getResource("/images/plane.png")).getImage();
        image_move = new ImageIcon(getClass().getResource("/images/plane_move.png")).getImage();
    }

    /**
     * Updates the player's position based on its current speed and angle.
     * Called on each frame in the game loop.
     */
    @Override
    public void update() {
        double dx = Math.cos(Math.toRadians(getAngle())) * speed;
        double dy = Math.sin(Math.toRadians(getAngle())) * speed;
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * Draws the player aircraft on the screen.
     * Displays different sprite when accelerating.
     *
     * @param g2 the Graphics2D object used for rendering
     */
    @Override
    public void draw(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();
        g2.translate(getX(), getY());

        AffineTransform tran = new AffineTransform();
        tran.rotate(Math.toRadians(getAngle() + 45), PLAYER_SIZE / 2, PLAYER_SIZE / 2);
        g2.drawImage(speedUp ? image_move : image, tran, null);

        g2.setTransform(oldTransform);
    }

    /**
     * Returns the shape of the player for collision detection.
     *
     * @return a rectangular shape representing the player’s hitbox
     */
    @Override
    public Shape getShape() {
        return new Rectangle((int) getX(), (int) getY(), (int) PLAYER_SIZE, (int) PLAYER_SIZE);
    }

    /**
     * Increases the player's speed gradually until reaching max speed.
     */
    public void speedUp() {
        speedUp = true;
        if (speed < MAX_SPEED) {
            speed += 0.01f;
        }
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
    }

    /**
     * Reduces the player's speed gradually until stopping.
     */
    public void speedDown() {
        speedUp = false;
        if (speed > 0) {
            speed -= 0.03f;
        }
        if (speed < 0) {
            speed = 0;
        }
    }

    /**
     * @return the horizontal center coordinate of the player
     */
    public double getCenterX() {
        return getX() + PLAYER_SIZE / 2;
    }

    /**
     * @return the vertical center coordinate of the player
     */
    public double getCenterY() {
        return getY() + PLAYER_SIZE / 2;
    }

    /**
     * Gets the current health points (HP) of the player.
     *
     * @return the current HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * Reduces the player's HP by the given amount of damage.
     *
     * @param dmg the amount of damage to apply
     */
    public void takeDamage(int dmg) {
        hp -= dmg;
    }

    /**
     * Checks if the player is dead (HP <= 0).
     *
     * @return true if the player is dead
     */
    public boolean isDead() {
        return hp <= 0;
    }

    /**
     * Sets the player's HP to the specified value.
     *
     * @param hp new health value
     */
    public void setHp(int hp) {
        this.hp = hp;
    }
}