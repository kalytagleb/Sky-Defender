package render.screen;

import java.awt.*;

/**
 * Renders the manual (how to play) screen.
 */
public class ManualRenderer {
    /**
     * Draws the manual screen with game instructions and a back button.
     *
     * @param g2 the graphics context
     * @param width the screen width
     * @param height the screen height
     * @param backButtonBounds the bounds of the back button
     */
    public void draw(Graphics2D g2, int width, int height, Rectangle backButtonBounds) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        String title = "HOW TO PLAY";
        FontMetrics titleFM = g2.getFontMetrics();
        int titleX = (width - titleFM.stringWidth(title)) / 2;
        g2.drawString(title, titleX, 100);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String[] lines = {
                "- A / D: Rotate the plane",
                "- SPACE: Accelerate forward",
                "- J: Fire Rocket",
                "- K: Fire Flame",
                "- L: Fire Laser",
                "- P: Pause / Unpause the game",
                "- S: Save game to file",
                "- X: Load game from file",
                "- Destroy enemies to gain score",
                "- Survive increasingly difficult enemy waves"
        };
        int y = 160;
        for (String line : lines) {
            g2.drawString(line, 60, y);
            y += 30;
        }

        String backText = "BACK";
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fmBack = g2.getFontMetrics();
        int backWidth = fmBack.stringWidth(backText) + 40;
        int backHeight = 40;
        int backX = (width - backWidth) / 2;
        int backY = height - 80;

        backButtonBounds.setBounds(backX, backY, backWidth, backHeight);

        g2.setColor(new Color(60, 60, 60));
        g2.fillRoundRect(backX, backY, backWidth, backHeight, 10, 10);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(backX, backY, backWidth, backHeight, 10, 10);
        g2.drawString(backText, backX + 20, backY + 28);
    }
}