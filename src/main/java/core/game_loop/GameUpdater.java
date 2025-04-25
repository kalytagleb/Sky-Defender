package core.game_loop;

import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Laser;
import service.WeaponControlService;
import service.collision.CollisionService;
import service.collision.PlayerCollisionService;
import service.game_state.state_pattern.GameOverState;
import service.waves.WaveManager;

import java.util.logging.Logger;

/**
 * Updates the game state, including object positions, collisions, and waves.
 */

public class GameUpdater {
    private static final Logger logger = Logger.getLogger(GameUpdater.class.getName());

    private final CollisionService collisionService = new CollisionService();
    private final PlayerCollisionService playerCollisionService = new PlayerCollisionService();
    private final WeaponControlService weaponControlService = new WeaponControlService();

    private long lastWaveCheckTime = System.currentTimeMillis();
    private final long WAVE_INTERVAL = 5000; // Every 5 seconds

    /**
     * Updates the game state, including objects, collisions, waves, and game over conditions.
     *
     * @param context the game context
     * @param width the screen width
     * @param height the screen height
     */
    public void update(GameContext context, int width, int height) {
        logger.fine("Updating game state...");
        updateObjects(context, width, height);
        updateCollisions(context);
        updateWaveLogic(context);
        checkGameOver(context);
    }

    /**
     * Updates the positions and states of game objects (player, enemies, weapons).
     *
     * @param context the game context
     * @param width the screen width
     * @param height the screen height
     */
    private void updateObjects(GameContext context, int width, int height) {
        weaponControlService.handleWeaponFire(context);

        context.getPlayer().update();
        context.getEnemies().forEach(AbstractEnemy::update);

        context.getWeapons().removeIf(w -> {
            boolean outOfBounds = !w.check(width, height);

            if (outOfBounds) {
                logger.fine("Weapon removed due to out of bounds: " + w.getClass().getSimpleName());
            }

            return outOfBounds;
        });

        context.getWeapons().forEach(AbstractWeapon::update);
    }

    /**
     * Checks and handles collisions between weapons, enemies, and the player.
     *
     * @param context the game context
     */
    private void updateCollisions(GameContext context) {
        logger.finer("Checking collisions...");

        collisionService.check(
                context.getWeapons(),
                context.getEnemies(),
                () -> {
                    context.setScore(context.getScore() + 1);
                    logger.fine("Enemy destroyed! Score increased to " + context.getScore());
                },
                weapon -> !(weapon instanceof Laser)
        );

        playerCollisionService.check(context.getPlayer(), context.getEnemies());
    }

    /**
     * Updates the wave logic, starting new waves based on time and score.
     *
     * @param context the game context
     */
    private void updateWaveLogic(GameContext context) {
        WaveManager waveManager = context.getWaveManager();

        long now = System.currentTimeMillis();
        if (now - lastWaveCheckTime >= WAVE_INTERVAL) {
            waveManager.startNextWave(context.getScore());
            logger.info("Starting new wave: " + waveManager.getWaveNumber());
            lastWaveCheckTime = now;
        }

        waveManager.updateWaveTextState();
    }

    /**
     * Checks for game over conditions (player death or out of bounds).
     *
     * @param context the game context
     */

    private void checkGameOver(GameContext context) {
        Player player = context.getPlayer();

        if (player.isDead()) {
            logger.warning("Game Over: Player is dead");
            context.getGameStateManager().setCurrentState(new GameOverState());
        } else if (isPlayerOutOfBounds(player, context.getWidth(), context.getHeight())) {
            logger.warning("Game Over: Player went out of bounds");
            context.getGameStateManager().setCurrentState(new GameOverState());
        }
    }

    /**
     * Checks if the player is ouf of bounds.
     *
     * @param player the player object
     * @param width the screen width
     * @param height the screen height
     * @return true if player is out of bounds, false otherwise
     */
    private boolean isPlayerOutOfBounds(Player player, int width, int height) {
        return player.getX() < -Player.PLAYER_SIZE ||
                player.getY() < -Player.PLAYER_SIZE ||
                player.getX() > width ||
                player.getY() > height;
    }
}
