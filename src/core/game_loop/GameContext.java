package core.game_loop;

import input.Key;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import service.game_state.GameStateManager;
import service.waves.WaveManager;

import java.util.List;

public class GameContext {
    private final Player player;
    private final List<AbstractEnemy> enemies;
    private final List<AbstractWeapon> weapons;
    private final Key key;
    private final WaveManager waveManager;
    private final GameStateManager gameStateManager;

    private int score = 0;

    public GameContext(Player player,
                       List<AbstractEnemy> enemies,
                       List<AbstractWeapon> weapons,
                       Key key,
                       WaveManager waveManager,
                       GameStateManager gameStateManager) {
        this.player = player;
        this.enemies = enemies;
        this.weapons = weapons;
        this.key = key;
        this.waveManager = waveManager;
        this.gameStateManager = gameStateManager;
    }

    public Player getPlayer() {
        return player;
    }

    public List<AbstractEnemy> getEnemies() {
        return enemies;
    }

    public List<AbstractWeapon> getWeapons() {
        return weapons;
    }

    public Key getKey() {
        return key;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}