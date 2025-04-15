package test.service.game_state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.game_state.GameStateManager;
import service.game_state.state_pattern.GameOverState;
import service.game_state.state_pattern.MainMenuState;
import service.game_state.state_pattern.ManualState;
import service.game_state.state_pattern.PlayingState;

import static org.junit.jupiter.api.Assertions.*;

class GameStateManagerTest {

    private GameStateManager gsm;

    @BeforeEach
    void setUp() {
        gsm = new GameStateManager();
    }

    @Test
    void initialStateShouldBeMainMenu() {
        gsm.setCurrentState(new MainMenuState());
        assertTrue(gsm.getCurrentState() instanceof MainMenuState);
    }

    @Test
    void canChangeState() {
        gsm.setCurrentState(new PlayingState());
        assertTrue(gsm.getCurrentState() instanceof PlayingState);
    }

    @Test
    void isReturnsTrueForCurrentState() {
        gsm.setCurrentState(new ManualState());
        assertTrue(gsm.getCurrentState() instanceof ManualState);
    }

    @Test
    void isReturnsFalseForDifferentState() {
        gsm.setCurrentState(new GameOverState());
        assertFalse(gsm.getCurrentState() instanceof PlayingState);
    }
}