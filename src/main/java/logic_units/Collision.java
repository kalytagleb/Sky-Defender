package logic_units;

import model.GameObject;

import java.awt.geom.Area;
/**
 * Utility class for checking collisions between game objects.
 */

public final class Collision {
    private Collision() {}

    /**
     * Checks if two {@link GameObject} instances intersect based on their shapes.
     *
     * @param a the first game object
     * @param b the second game object
     * @return {@code true} if the objects intersect, {@code false} otherwise
     */
    public static boolean intersects(GameObject a, GameObject b) {
        Area areaA = new Area(a.getShape());
        areaA.intersect(new Area(b.getShape()));
        return !areaA.isEmpty();
    }
}