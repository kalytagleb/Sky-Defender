package service.game_state;

public class GameStateManager {
    private GameState currentState = GameState.MAIN_MENU;

    public GameState getState() {
        return currentState;
    }

    public void setState(GameState newState) {
        this.currentState = newState;
    }

    public boolean is(GameState state) {
        return currentState == state;
    }
}