package data.enemy;

import java.io.Serializable;

/**
 * Represents the main.core.data for an enemy in the saved game state.
 * Implements Serializable to enable saving to a file.
 */
public class EnemyData implements Serializable {
    private final String type;
    private final double x, y;
    private final float angle;
    private final int hp;

    /**
     * Constructs an EnemyData object with the specified attributes.
     *
     * @param type the type of enemy
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param angle the rotation angle
     * @param hp the health points
     */
    public EnemyData(String type, double x, double y, float angle, int hp) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.hp = hp;
    }

    /**
     * Gets the enemy type.
     *
     * @return the enemy type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the x-coordinate.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate
     *
     * @return the y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the rotation angle.
     *
     * @return the angle
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Gets the health points.
     *
     * @return the health points
     */
    public int getHp() {
        return hp;
    }
}