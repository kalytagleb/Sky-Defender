package service.keyboard;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import core.game_loop.GameRestarter;
import data.SaveManager;
import factory.weapon.RocketFactory;
import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.game_state.GameStateManager;
import service.game_state.state_pattern.MainMenuState;
import service.game_state.state_pattern.PausedState;
import service.game_state.state_pattern.PlayingState;
import service.keyboard.KeyboardHandlerService;
import service.waves.WaveManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeyboardHandlerServiceTest {

    private KeyboardHandlerService handler;
    private GameContext context;
    private GameStateManager gsm;
    private Key key;
    private SaveManager saveManager;
    private GameRestarter restarter;
    private GameRenderer renderer;

    @BeforeEach
    void setUp() {
        saveManager = new SaveManager();
        restarter = new GameRestarter();
        handler = new KeyboardHandlerService(saveManager, restarter);

        key = new Key();
        gsm = new GameStateManager();
        Player player = new Player();
        List<AbstractEnemy> enemies = new ArrayList<>();
        List<AbstractWeapon> weapons = new ArrayList<>();
        WaveManager waveManager = new WaveManager(enemies, 800, 600);
        context = new GameContext(player, enemies, weapons, key, waveManager, gsm, 800, 600);

        renderer = new GameRenderer();
    }

    @Test
    void setsGameStateToPlayingOnEnterFromMainMenu() {
        gsm.setCurrentState(new MainMenuState());
        KeyEvent e = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_ENTER, ' ');

        handler.handleKeyPressed(e, context, renderer, 800);

        assertTrue(gsm.getCurrentState() instanceof PlayingState);
    }

    @Test
    void setsGameStateToPausedOnPWhenPlaying() {
        gsm.setCurrentState(new PlayingState());
        KeyEvent e = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_P, 'P');

        handler.handleKeyPressed(e, context, renderer, 800);

        assertTrue(gsm.getCurrentState() instanceof PausedState);
    }

    @Test
    void setsKeyLeftTrueWhenAPressed() {
        KeyEvent e = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_A, 'A');
        handler.handleKeyPressed(e, context, renderer, 800);
        assertTrue(key.isKey_left());
    }

    @Test
    void setsKeyJTrueAndFactoryRocketOnJPressed() {
        KeyEvent e = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_J, 'J');
        handler.handleKeyPressed(e, context, renderer, 800);
        assertTrue(key.isKey_j());
        assertTrue(context.getWeaponFactory() instanceof RocketFactory);
    }

    @Test
    void handleKeyReleasedDisableKeys() {
        key.setKey_j(true);
        KeyEvent e = new KeyEvent(new Component() {}, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_J, 'J');
        handler.handleKeyReleased(e, key);
        assertFalse(key.isKey_j());
    }
}