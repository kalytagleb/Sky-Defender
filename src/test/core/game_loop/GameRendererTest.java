package test.core.game_loop;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.game_state.GameStateManager;
import service.game_state.state_pattern.*;
import service.waves.WaveManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameRendererTest {

    private GameRenderer renderer;
    private GameContext context;
    private GameStateManager gsm;
    private Player player;
    private List<AbstractEnemy> enemies;
    private List<AbstractWeapon> weapons;
    private Key key;
    private WaveManager waveManager;
    private Graphics2D g2;

    @BeforeEach
    void setUp() {
        renderer = new GameRenderer();
        gsm = new GameStateManager();
        player = new Player();
        enemies = new CopyOnWriteArrayList<>();
        weapons = new CopyOnWriteArrayList<>();
        key = new Key();
        waveManager = new WaveManager(enemies, 800, 600);
        context = new GameContext(player, enemies, weapons, key, waveManager, gsm, 800, 600);

        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
    }

    @Test
    void drawInMainMenuStateDoesNotThrow() {
        context.getGameStateManager().setCurrentState(new MainMenuState());
        assertDoesNotThrow(() -> renderer.draw(g2, context, 800, 600));
    }

    @Test
    void drawInManualStateDoesNotThrow() {
        context.getGameStateManager().setCurrentState(new ManualState());
        assertDoesNotThrow(() -> renderer.draw(g2, context, 800, 600));
    }

    @Test
    void drawInGameOverStateDoesNotThrow() {
        context.getGameStateManager().setCurrentState(new GameOverState());
        assertDoesNotThrow(() -> renderer.draw(g2, context, 800, 600));
    }

    @Test
    void drawInPausedStateDoesNotThrow() {
        context.getGameStateManager().setCurrentState(new PausedState());
        assertDoesNotThrow(() -> renderer.draw(g2, context, 800, 600));
    }

    @Test
    void drawInPlayingStateStateDoesNotThrow() {
        context.getGameStateManager().setCurrentState(new PlayingState());
        assertDoesNotThrow(() -> renderer.draw(g2, context, 800, 600));
    }

    @Test
    void manualButtonBoundsAreAccessible() {
        Rectangle bounds = renderer.getManualButtonBounds();
        assertNotNull(bounds);
    }

    @Test
    void manualBackButtonAreAccessible() {
        Rectangle bounds = renderer.getManualBackButtonBounds();
        assertNotNull(bounds);
    }

    @Test
    void showNotificationDoesNotThrow() {
        assertDoesNotThrow(() -> renderer.showNotification("Test message"));
    }
}