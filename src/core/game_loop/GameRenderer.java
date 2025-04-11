package core.game_loop;

import java.awt.*;

public class GameRenderer {
    public void draw(Graphics2D g2, GameContext context, int width, int height) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        context.getPlayer().draw(g2);
        context.getWeapons().forEach(w -> w.draw(g2));
        context.getEnemies().forEach(e -> e.draw(g2));
    }
}