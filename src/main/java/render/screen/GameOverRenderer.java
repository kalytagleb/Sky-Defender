package render.screen;

import java.awt.*;

/**
 * Renders the game over screen.
 */
public class GameOverRenderer {
    /**
     * Draws the game over screen with the final score and restart/exit prompts.
     *
     * @param g2 the graphics context
     * @param width the screen width
     * @param height the screen height
     * @param score the final score
     */
    public void draw(Graphics2D g2, int width, int height, int score) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 80));
        String message = "Game Over";
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(message)) / 2;
        int y = (height - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(message, x, y);

        String scoreMsg = "Score: " + score;
        g2.setColor(new Color(255, 215, 0));
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        FontMetrics scoreFM = g2.getFontMetrics();
        int scoreX = (width - scoreFM.stringWidth(scoreMsg)) / 2;
        int scoreY = y + 100;
        g2.drawString(scoreMsg, scoreX, scoreY);

        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String hint = "Please ESC to exit" + " | Press R to restart";
        int hintX = (width - g2.getFontMetrics().stringWidth(hint)) / 2;
        int hintY = height - 50;
        g2.drawString(hint, hintX, hintY);
    }
}