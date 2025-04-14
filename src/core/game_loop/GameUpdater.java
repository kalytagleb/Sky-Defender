package core.game_loop;

import input.Key;
import logic_units.Collision;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Laser;
import service.WeaponControlService;
import service.collision.CollisionService;
import service.collision.PlayerCollisionService;
import service.game_state.GameState;
import service.waves.WaveManager;

import java.util.logging.Logger;

public class GameUpdater {
    private static final Logger logger = Logger.getLogger(GameUpdater.class.getName());

    private final CollisionService collisionService = new CollisionService();
    private final PlayerCollisionService playerCollisionService = new PlayerCollisionService();
    private final WeaponControlService weaponControlService = new WeaponControlService();

    private long lastWaveCheckTime = System.currentTimeMillis();
    private final long WAVE_INTERVAL = 5000; // каждые 5 секунд

    public void update(GameContext context, int width, int height) {
        logger.fine("Updating game state...");
        updateObjects(context, width, height);
        updateCollisions(context);
        updateWaveLogic(context);
        checkGameOver(context);
    }

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

    private void checkGameOver(GameContext context) {
        Player player = context.getPlayer();

        if (player.isDead()) {
            logger.warning("Game Over: Player is dead");
            context.getGameStateManager().setState(GameState.GAME_OVER);
        } else if (isPlayerOutOfBounds(player, context.getWidth(), context.getHeight())) {
            logger.warning("Game Over: Player went out of bounds");
            context.getGameStateManager().setState(GameState.GAME_OVER);
        }
    }

    private boolean isPlayerOutOfBounds(Player player, int width, int height) {
        return player.getX() < -Player.PLAYER_SIZE ||
                player.getY() < -Player.PLAYER_SIZE ||
                player.getX() > width ||
                player.getY() > height;
    }
}
