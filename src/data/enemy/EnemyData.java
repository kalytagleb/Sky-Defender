package data.enemy;

import java.io.Serializable;

public class EnemyData implements Serializable {
    private final String type;
    private final double x, y;
    private final float angle;
    private final int hp;

    public EnemyData(String type, double x, double y, float angle, int hp) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.hp = hp;
    }

    public String getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public float getAngle() {
        return angle;
    }

    public int getHp() {
        return hp;
    }
}