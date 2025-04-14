package test.render.game_scene;

import model.enemies.AbstractEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import render.game_scene.WaveTextRenderer;
import service.waves.WaveManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WaveTextRendererTest {

    private BufferedImage image;
    private Graphics2D g2;
    private WaveTextRenderer renderer;
    private WaveManager waveManager;

    @BeforeEach
    void setUp() {
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        List<AbstractEnemy> enemies = new CopyOnWriteArrayList<>();
        waveManager = new WaveManager(enemies, 800, 600);
        renderer = new WaveTextRenderer();

        waveManager.startNextWave(0);
    }

    @Test
    void drawWaveTextWhenVisible() {
        renderer.draw(g2, waveManager, 800, 600);

        boolean isDrawn = false;

        for (int dx = -5; dx <= 5; dx++) {
            for (int dy = -5; dy <= 5; dy++) {
                int x = 400 + dx;
                int y = 300 + dy;
                Color colorPixel = new Color(image.getRGB(x, y), true);
                if (colorPixel.getAlpha() > 0 && !colorPixel.equals(Color.BLACK)) {
                    isDrawn = true;
                    break;
                }
            }
            if (isDrawn) break;
        }

        assertTrue(isDrawn, "Expected wave text to be drawn near the center");
    }
}