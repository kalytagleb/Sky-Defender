package render.screen;

import java.awt.*;

public class NotificationRenderer {

    private String message = "";
    private long messageStartTime = 0;
    private final int DURATION = 2000;

    public void showMessage(String message) {
        this.message = message;
        this.messageStartTime = System.currentTimeMillis();
    }

    public void draw(Graphics2D g2, int width) {
        if (System.currentTimeMillis() - messageStartTime > DURATION) return;

        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.WHITE);

        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(message);
        int x = (width - textWidth) / 2;
        int y = 60;

        g2.drawString(message, x, y);
    }
}