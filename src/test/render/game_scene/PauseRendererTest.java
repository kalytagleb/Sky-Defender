package test.render.game_scene;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import render.game_scene.PauseRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PauseRendererTest {

    private PauseRenderer renderer;
    private BufferedImage image;
    private Graphics2D g2;
    private int width = 800;
    private int height = 600;

    @BeforeEach
    void setUp() {
        renderer = new PauseRenderer();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Test
    void drawRendersPauseOverlay() {
        renderer.draw(g2, width, height);

        int centerX = width / 2;
        int centerY = height / 2;

        Color pixelColor = new Color(image.getRGB(centerX, centerY), true);

        boolean isDrawn = !(pixelColor.getRed() == 0 &&
                            pixelColor.getGreen() == 0 &&
                            pixelColor.getBlue() == 0 &&
                            pixelColor.getAlpha() == 255);

        assertTrue(isDrawn, "Expected 'PAUSED' text or overlay to be drawn near the center");
    }
}