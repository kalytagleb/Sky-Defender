package render.screen;

import java.awt.*;

/**
 * Renders the main menu screen.
 */
public class MainMenuRenderer {
    /**
     * Draws the main menu with title, start prompt, and "How to Play" button.
     *
     * @param g2 the graphics context
     * @param width the screen width
     * @param height the screen height
     * @param manualButtonBounds the bounds of the "How to Play" button
     */
    public void draw(Graphics2D g2, int width, int height, Rectangle manualButtonBounds) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.CYAN);
        g2.setFont(new Font("Arial", Font.BOLD, 64));
        String title = "Sky Defender";
        FontMetrics titleFM = g2.getFontMetrics();
        int titleX = (width - titleFM.stringWidth(title)) / 2;
        int titleY = height / 2 - 100;
        g2.drawString(title, titleX, titleY);

        g2.setColor(new Color(255, 215, 0));
        g2.setFont(new Font("Arial", Font.BOLD, 32));
        String startMsg = "Press ENTER to start";
        FontMetrics msgFM = g2.getFontMetrics();
        int msgX = (width - msgFM.stringWidth(startMsg)) / 2;
        int msgY = titleY + 100;
        g2.drawString(startMsg, msgX, msgY);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String escHint = "Press ESC to quit";
        int escX = (width - g2.getFontMetrics().stringWidth(escHint)) / 2;
        int escY = height - 50;
        g2.drawString(escHint, escX, escY);

        String buttonText = "HOW TO PLAY";
        g2.setFont(new Font("Arial", Font.BOLD, 28));
        FontMetrics fmBtn = g2.getFontMetrics();
        int btnWidth = fmBtn.stringWidth(buttonText) + 40;
        int btnHeight = 40;
        int btnX = (width - btnWidth) / 2;
        int btnY = height / 2 + 50;

        manualButtonBounds.setBounds(btnX, btnY, btnWidth, btnHeight);

        g2.setColor(new Color(70, 70, 70));
        g2.fillRoundRect(btnX, btnY, btnWidth, btnHeight, 15, 15);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(btnX, btnY, btnWidth, btnHeight, 15, 15);
        g2.drawString(buttonText, btnX + 20, btnY + 28);
    }
}