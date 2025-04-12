package logic_units;

import java.awt.geom.Area;
import java.awt.geom.Path2D;

public class EnemyShapeUtil {
    public static Area createDefaultEnemyShape(double size) {
        Path2D p = new Path2D.Double();
        p.moveTo(0, size / 2);
        p.lineTo(size * 0.3, size * 0.2);
        p.lineTo(size * 0.85, size * 0.26);
        p.lineTo(size + 10, size / 2);
        p.lineTo(size * 0.85, size * 0.74);
        p.lineTo(size * 0.3, size * 0.8);
        p.closePath();
        return new Area(p);
    }
}