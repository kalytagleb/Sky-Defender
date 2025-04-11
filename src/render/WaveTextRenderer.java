package render;

import service.WaveManager;

import java.awt.*;

public class WaveTextRenderer {
    public void draw(Graphics2D g2, WaveManager waveManager, int width, int height) {
        if (!waveManager.shouldShowWaveText()) return;

        String text = "WAVE " + (waveManager.getWaveNumber() - 1);

        g2.setFont(new Font("Arial", Font.BOLD, 36));
        g2.setColor(Color.YELLOW);

        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = height / 2;

        g2.drawString(text, x, y);
    }
}