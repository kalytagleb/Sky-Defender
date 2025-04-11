package core.game_loop;

import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Laser;
import service.CollisionService;

public class GameUpdater {
    private final CollisionService collisionService = new CollisionService();

    public void update(GameContext context, int width, int height) {
        context.getPlayer().update();

        context.getEnemies().forEach(AbstractEnemy::update);
        context.getWeapons().removeIf(w -> !w.check(width, height));
        context.getWeapons().forEach(AbstractWeapon::update);

        collisionService.check(
                context.getWeapons(),
                context.getEnemies(),
                context::increaseScore,
                weapon -> !(weapon instanceof Laser)
        );

        context.getWaveManager().trySpawnWave(context.getScore());
        context.getWaveManager().updateWaveTextState();
    }
}