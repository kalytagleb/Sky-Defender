package test.service.game_state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.game_state.GameState;
import service.game_state.GameStateManager;

import static org.junit.jupiter.api.Assertions.*;

class GameStateManagerTest {

    private GameStateManager gsm;

    @BeforeEach
    void setUp() {
        gsm = new GameStateManager();
    }

    @Test
    void initialStateShouldBeMainMenu() {
        assertEquals(GameState.MAIN_MENU, gsm.getState());
    }

    @Test
    void canChangeState() {
        gsm.setState(GameState.PLAYING);
        assertEquals(GameState.PLAYING, gsm.getState());
    }

    @Test
    void isReturnsTrueForCurrentState() {
        gsm.setState(GameState.MANUAL);
        assertTrue(gsm.is(GameState.MANUAL));
    }

    @Test
    void isReturnsFalseForDifferentState() {
        gsm.setState(GameState.GAME_OVER);
        assertFalse(gsm.is(GameState.PLAYING));
    }
}