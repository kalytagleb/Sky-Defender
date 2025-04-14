package test.render.game_scene;

import core.game_loop.GameContext;
import input.Key;
import model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import render.game_scene.HudRenderer;
import service.game_state.GameStateManager;
import service.waves.WaveManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HudRendererTest {

    private HudRenderer hudRenderer;
    private GameContext context;
    private Player player;
    private BufferedImage image;
    private Graphics2D g2;

    @BeforeEach
    void setUp() {
        hudRenderer = new HudRenderer();
        player = new Player();
        player.setHp(80);
        context = new GameContext(
                player,
                new ArrayList<>(),
                new ArrayList<>(),
                new Key(),
                new WaveManager(new ArrayList<>(), 800, 600),
                new GameStateManager(),
                1366, 768
        );
        context.setScore(100);

        image = new BufferedImage(1366, 768, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
    }

    @AfterEach
    void tearDown() {
        g2.dispose();
    }

    @Test
    void drawDrawsGreenHpBarForHighHp() {
        player.setHp(90);
        hudRenderer.draw(g2, context);

        int barX = 1366 - 200 - 20;
        int barY = 30 - 16 / 2;

        Color color = new Color(image.getRGB(barX + 5, barY + 5));
        assertTrue(color.getGreen() > color.getRed(), "HP bar should be green for high HP");
    }

    @Test
    void drawDrawsRedHpBarForLowHp() {
        player.setHp(10);
        hudRenderer.draw(g2, context);

        int barX = 1366 - 200 - 20;
        int barY = 30 - 16 / 2;

        Color color = new Color(image.getRGB(barX + 5, barY + 5));
        assertTrue(color.getRed() > color.getGreen(), "HP bar should be red for low HP");
    }
}