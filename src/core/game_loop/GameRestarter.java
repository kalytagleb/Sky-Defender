package core.game_loop;

import model.Player;

/**
 * Resets the game state to its initial conditions.
 */
public class GameRestarter {
    /**
     * Restarts the game by resetting player, enemies, weapons, and score.
     *
     * @param context the game context
     * @param width the screen width
     * @param height the screen height
     */
    public void restart(GameContext context, int width, int height) {
        context.getEnemies().clear();
        context.getWeapons().clear();
        context.setScore(0);

        Player player = context.getPlayer();
        player.changeLocation(width / 2.0, height / 2.0);
        player.setAngle(0);
        player.setHp(100);
    }
}