package service.keyboard;

import core.game_loop.GameContext;
import core.game_loop.GameRenderer;
import core.game_loop.GameRestarter;
import data.SaveManager;
import exceptions.GameLoadException;
import factory.weapon.FlameFactory;
import factory.weapon.LaserFactory;
import factory.weapon.RocketFactory;
import input.Key;
import service.game_state.GameStateManager;
import service.game_state.state_pattern.*;
import service.game_state.state_pattern.*;

import java.awt.event.KeyEvent;

/**
 * Handles keyboard main.input events and updates the game state accordingly.
 */
public class KeyboardHandlerService {
    /** The save manager for saving and loading game state */
    private final SaveManager saveManager;
    /** The game restarter for resetting the game */
    private final GameRestarter restarter;

    /**
     * Constructs a keyboard handler main.service with the specified save manager and game restarter.
     *
     * @param saveManager the save manager for handling game saves
     * @param restarter the game restarter for resetting the game
     */
    public KeyboardHandlerService(SaveManager saveManager, GameRestarter restarter) {
        this.saveManager = saveManager;
        this.restarter = restarter;
    }

    /**
     * Handles key press events, updating the game state and key states based on main.input.
     *
     * @param e the key event
     * @param context the game context containing game main.core.data
     * @param renderer the game renderer for displaying notifications
     * @param width the screen width
     */
    public void handleKeyPressed(KeyEvent e, GameContext context, GameRenderer renderer, int width) {
        GameStateManager gsm = context.getGameStateManager();
        Key key = context.getKey();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> System.exit(0);
            case KeyEvent.VK_ENTER -> {
                if (context.getGameStateManager().getCurrentState() instanceof MainMenuState) {
                    context.getGameStateManager().setCurrentState(new PlayingState());
                    restarter.restart(context, width, context.getHeight());
                }
            }
            case KeyEvent.VK_R -> {
                if (context.getGameStateManager().getCurrentState() instanceof GameOverState) {
                    context.getGameStateManager().setCurrentState(new PlayingState());
                    restarter.restart(context, width, context.getHeight());
                }
            }
            case KeyEvent.VK_M -> context.getGameStateManager().setCurrentState(new ManualState());
            case KeyEvent.VK_P -> {
                if (context.getGameStateManager().getCurrentState() instanceof PlayingState)
                    context.getGameStateManager().setCurrentState(new PausedState());
                else if (context.getGameStateManager().getCurrentState() instanceof PausedState) {
                    context.getGameStateManager().setCurrentState(new PlayingState());
                }
            }
            case KeyEvent.VK_S -> {
                saveManager.asyncSave(context, renderer, width);
                renderer.showNotification("Game saved!");
            }
            case KeyEvent.VK_X -> {
                try {
                    saveManager.load(context, renderer, width);
                    renderer.showNotification("Game loaded!");
                } catch (GameLoadException ex) {
                    renderer.showNotification("Error: " + ex.getMessage());
                }
            }
            case KeyEvent.VK_J -> {
                context.setWeaponFactory(new RocketFactory());
                key.setKey_j(true);
            }
            case KeyEvent.VK_K -> {
                context.setWeaponFactory(new FlameFactory());
                key.setKey_k(true);
            }
            case KeyEvent.VK_L -> {
                context.setWeaponFactory(new LaserFactory());
                key.setKey_l(true);
            }
            case KeyEvent.VK_A -> key.setKey_left(true);
            case KeyEvent.VK_D -> key.setKey_right(true);
            case KeyEvent.VK_SPACE -> key.setKey_space(true);
        }
    }

    /**
     * Handles key release events, updating key states.
     *
     * @param e the key event
     * @param key the key state object
     */
    public void handleKeyReleased(KeyEvent e, Key key) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> key.setKey_left(false);
            case KeyEvent.VK_D -> key.setKey_right(false);
            case KeyEvent.VK_SPACE -> key.setKey_space(false);
            case KeyEvent.VK_J -> key.setKey_j(false);
            case KeyEvent.VK_K -> key.setKey_k(false);
            case KeyEvent.VK_L -> key.setKey_l(false);
        }
    }
}