package render.screen;

import java.awt.*;

public class ManualRenderer {
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
                "- A / D: Rotate plane",
                "- SPACE: Accelerate",
                "- J: Rocket  |  K: Flame  |  L: Laser",
                "- Destroy enemies to gain score",
                "- Survive waves that increase in difficulty"
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