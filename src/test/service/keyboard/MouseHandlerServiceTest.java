package test.service.keyboard;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import input.Key;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.game_state.GameState;
import service.game_state.GameStateManager;
import service.keyboard.MouseHandlerService;
import service.waves.WaveManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MouseHandlerServiceTest {

    private MouseHandlerService service;
    private GameContext context;
    private GameRenderer renderer;

    @BeforeEach
    void setUp() {
        service = new MouseHandlerService();

        Player player = new Player();
        GameStateManager gsm = new GameStateManager();
        context = new GameContext(
                player,
                new CopyOnWriteArrayList<>(),
                new CopyOnWriteArrayList<>(),
                new Key(),
                new WaveManager(new CopyOnWriteArrayList<>(), 800, 600),
                gsm,
                800,
                600
        );

        renderer = new GameRenderer();

        Rectangle manualBtn = renderer.getManualButtonBounds();
        manualBtn.setBounds(100, 100, 200, 50);
        Rectangle backBtn = renderer.getManualBackButtonBounds();
        backBtn.setBounds(100, 100, 200, 50);
    }

    @Test
    void clickManualButton() {
        context.getGameStateManager().setState(GameState.MAIN_MENU);

        renderer.getManualButtonBounds().setBounds(300, 350, 200, 40);

        MouseEvent e = new MouseEvent(new Component() {}, MouseEvent.MOUSE_CLICKED, 0, 0, 320, 360, 1, false);
        service.handleClick(e, context, renderer);

        assertEquals(GameState.MANUAL, context.getGameStateManager().getState());
    }

    @Test
    void clickBackButton() {
        context.getGameStateManager().setState(GameState.MANUAL);

        MouseEvent e = new MouseEvent(new Component() {}, MouseEvent.MOUSE_CLICKED, 0, 0, 150, 120, 1, false);
        service.handleClick(e, context, renderer);

        assertEquals(GameState.MAIN_MENU, context.getGameStateManager().getState());
    }

    @Test
    void clickOutsideDoesNothing() {
        context.getGameStateManager().setState(GameState.MAIN_MENU);

        MouseEvent e = new MouseEvent(new Component() {}, MouseEvent.MOUSE_CLICKED, 0, 0, 10, 10, 1, false);
        service.handleClick(e, context, renderer);

        assertEquals(GameState.MAIN_MENU, context.getGameStateManager().getState());
    }
}