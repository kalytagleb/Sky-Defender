package core.game_loop;

import input.Key;
import logic_units.Collision;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Laser;
import service.WeaponControlService;
import service.collision.CollisionService;
import service.collision.PlayerCollisionService;
import service.game_state.GameState;

public class GameUpdater {

    private final CollisionService collisionService = new CollisionService();
    private final PlayerCollisionService playerCollisionService = new PlayerCollisionService();
    private final WeaponControlService weaponControlService = new WeaponControlService();

    public void update(GameContext context, int width, int height) {
        updateObjects(context, width, height);

        weaponControlService.handleWeaponFire(context);

        collisionService.check(
                context.getWeapons(),
                context.getEnemies(),
                () -> context.setScore(context.getScore() + 1),
                weapon -> !(weapon instanceof Laser)
        );

        context.getWaveManager().trySpawnWave(context.getScore());
        context.getWaveManager().updateWaveTextState();

        if (context.getPlayer().isDead()) {
            context.getGameStateManager().setState(GameState.GAME_OVER);
        }

        playerCollisionService.check(context.getPlayer(), context.getEnemies());
    }

    private void updateObjects(GameContext context, int width, int height) {
        context.getPlayer().update();
        context.getEnemies().forEach(AbstractEnemy::update);
        context.getWeapons().removeIf(w -> !w.check(width, height));
        context.getWeapons().forEach(AbstractWeapon::update);
    }
}