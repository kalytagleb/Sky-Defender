package render.screen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import render.screen.GameOverRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameOverRendererTest {

    private GameOverRenderer renderer;
    private BufferedImage image;
    private Graphics2D g2;

    @BeforeEach
    void setUp() {
        renderer = new GameOverRenderer();
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Test
    void drawRendersGameOverScreen() {
        renderer.draw(g2, 800, 600, 123);

        boolean hasNonBlackPixel = false;
        for (int y = 290; y <= 310; y++) {
            for (int x = 390; x <= 410; x++) {
                Color pixelColor = new Color(image.getRGB(x, y), true);
                if (pixelColor.getAlpha() > 0 && !pixelColor.equals(Color.BLACK)) {
                    hasNonBlackPixel = true;
                    break;
                }
            }
            if (hasNonBlackPixel) break;
        }

        assertTrue(hasNonBlackPixel, "Expected game over text to be drawn in center area");
    }
}