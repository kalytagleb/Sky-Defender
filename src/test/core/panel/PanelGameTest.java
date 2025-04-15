package test.core.panel;

import core.game_loop.GameContext;
import core.panel.PanelGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PanelGameTest {

    private PanelGame panel;

    @BeforeEach
    void setUp() {
        panel = new PanelGame();
        panel.setSize(800, 600);
        panel.start();
    }

    @Test
    void panelInitializesComponentsCorrectly() {
        assertNotNull(panel.getG2(), "Graphics2D must be initialized");
        assertNotNull(panel.getImage(), "BufferedImage must be initialized");
        assertTrue(panel.getWidthValue() > 0, "Width must be positive");
        assertTrue(panel.getHeightValue() > 0, "Height must be positive");
    }

    @Test
    void isStartReturnsTrueByDefault() {
        assertTrue(panel.isStart(), "Start should be true by default");
    }
}