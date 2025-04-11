package core.game_loop;

import model.Player;

public class GameRestarter {
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