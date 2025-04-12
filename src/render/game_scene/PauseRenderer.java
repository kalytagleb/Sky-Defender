package render.game_scene;

import java.awt.*;

public class PauseRenderer {
    public void draw(Graphics2D g2, int width, int height) {
        Composite original = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setComposite(original);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 64));
        String paused = "PAUSED";
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(paused)) / 2;
        int y = height / 2;
        g2.drawString(paused, x, y);
    }
}