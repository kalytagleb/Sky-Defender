package render.screen;

import core.game_loop.GameContext;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import render.screen.MainMenuRenderer;
import service.game_state.GameStateManager;
import service.waves.WaveManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainMenuRendererTest {

    private MainMenuRenderer renderer;
    private BufferedImage image;
    private Graphics2D g2;
    private Rectangle manualButtonBounds;
    private GameContext context;

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
        context = createTestContextWithHighScore(130);

        renderer.draw(g2, 800, 600, manualButtonBounds, context);

        int btnCenterX = manualButtonBounds.x + manualButtonBounds.width / 2;
        int btnCenterY = manualButtonBounds.y + manualButtonBounds.height / 2;

        Color pixelColor = new Color(image.getRGB(btnCenterX, btnCenterY), true);

        assertTrue(pixelColor.getAlpha() > 0, "Expected drawing at HOW TO PLAY button area");
    }

    @Test
    void drawSetsManualButtonBoundsCorrectly() {
        context = createTestContextWithHighScore(130);

        renderer.draw(g2, 800, 600, manualButtonBounds, context);

        assertTrue(manualButtonBounds.width > 0, "Manual button should have width");
        assertTrue(manualButtonBounds.height > 0, "Manual button should have height");
    }

    private GameContext createTestContextWithHighScore(int highScore) {
        Player player = new Player();
        player.setHp(100);

        List<AbstractEnemy> enemies = new ArrayList<>();
        List<AbstractWeapon> weapons = new ArrayList<>();

        Key key = new Key();
        GameStateManager gsm = new GameStateManager();
        WaveManager waveManager = new WaveManager(enemies, 800, 600);

        GameContext context = new GameContext(
                player, enemies, weapons, key, waveManager, gsm, 800, 600
        );
        context.setHighScore(highScore);
        return context;
    }
}