package render.screen;

import java.awt.*;

/**
 * Renders notification messages on the screen.
 */
public class NotificationRenderer {

    private String message = "";
    private long messageStartTime = 0;
    private final int DURATION = 2000;

    /**
     * Displays a notification message for a limited duration.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        this.message = message;
        this.messageStartTime = System.currentTimeMillis();
    }

    /**
     * Draws the notification message if it is still active.
     *
     * @param g2 the graphics context
     * @param width the screen width
     */
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