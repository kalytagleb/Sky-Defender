package data.weapon;

import java.io.Serializable;

public class WeaponData implements Serializable {
    private final String type;
    private final double x, y;
    private final float angle;
    private final int damage;

    public WeaponData(String type, double x, double y, float angle, int damage) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.damage = damage;
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

    public int getDamage() {
        return damage;
    }
}