package test.render.screen;

import core.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import render.screen.MainMenuRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainMenuRendererTest {

    private MainMenuRenderer renderer;
    private BufferedImage image;
    private Graphics2D g2;
    private Rectangle manualButtonBounds;

    @BeforeEach
    void setUp() {
        renderer = new MainMenuRenderer();
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        manualButtonBounds = new Rectangle();
    }

    @Test
    void drawRendersMainMenuComponents() {
        renderer.draw(g2, 800, 600, manualButtonBounds);

        int btnCenterX = manualButtonBounds.x + manualButtonBounds.width / 2;
        int btnCenterY = manualButtonBounds.y + manualButtonBounds.height / 2;

        Color pixelColor = new Color(image.getRGB(btnCenterX, btnCenterY), true);

        assertTrue(pixelColor.getAlpha() > 0, "Expected drawing at HOW TO PLAY button area");
    }

    @Test
    void drawSetsManualButtonBoundsCorrectly() {
        renderer.draw(g2, 800, 600, manualButtonBounds);

        assertTrue(manualButtonBounds.width > 0, "Manual button should have width");
        assertTrue(manualButtonBounds.height > 0, "Manual button should have height");
        assertTrue(manualButtonBounds.y > 0, "Manual button Y should be set");
    }
}