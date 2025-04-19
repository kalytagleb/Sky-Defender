package render.game_scene;

import core.game_loop.GameContext;
import service.waves.WaveManager;

import java.awt.*;

/**
 * Renders wave number text during wave transitions.
 */
public class WaveTextRenderer {
    /**
     * Draws the wave number text if it should be displayed.
     *
     * @param g2 the graphics context
     * @param waveManager the wave manager
     * @param width the screen width
     * @param height the screen height
     */
    public void draw(Graphics2D g2, WaveManager waveManager, int width, int height) {
        if (!waveManager.shouldShowWaveText()) return;

        String text = "WAVE " + waveManager.getWaveNumber();

        g2.setFont(new Font("Arial", Font.BOLD, 36));
        g2.setColor(Color.YELLOW);

        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = height / 2;

        g2.drawString(text, x, y);
    }
}