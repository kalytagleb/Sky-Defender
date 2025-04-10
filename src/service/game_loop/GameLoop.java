package service.game_loop;

import controller.Key;
import controller.PanelGame;
import model.GameObject;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import model.weapon.Weapon;
import model.weapon.factory.WeaponFactory;

import java.awt.*;
import java.util.List;

public class GameLoop {
    private final PanelGame panel;
    private final Player player;
    private final List<AbstractEnemy> enemies;
    private final List<AbstractWeapon> weapons;
    private final Key key;
    private WeaponFactory weaponFactory;

    private final int TARGET_TIME = 1000000000 / 60;
    private Thread thread;

    public GameLoop(PanelGame panel, Player player,
                    List<AbstractEnemy> enemies, List<AbstractWeapon> weapons,
                    Key key, WeaponFactory weaponFactory) {
        this.panel = panel;
        this.player = player;
        this.enemies = enemies;
        this.weapons = weapons;
        this.key = key;
        this.weaponFactory = weaponFactory;
    }

    public void start() {
        thread = new Thread(() -> {
            while (panel.isStart()) {
                long startTime = System.nanoTime();

                update();
                draw();
                render();

                long time = System.nanoTime() - startTime;
                if (time < TARGET_TIME) {
                    sleep((TARGET_TIME - time) / 1000000);
                }
            }
        });
        thread.start();
    }

    private void update() {
        player.update();

        for (AbstractEnemy enemy : enemies) {
            enemy.update();
        }

        for (AbstractWeapon weapon : weapons) {
            weapon.update();
        }
    }

    private void draw() {
        Graphics2D g2 = panel.getG2();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, panel.getWidthValue(), panel.getHeightValue());

        player.draw(g2);
        for (AbstractWeapon weapon : weapons) weapon.draw(g2);
        for (AbstractEnemy enemy : enemies) enemy.draw(g2);
    }

    /**
     * Draws the off-screen image onto the screen.
     */
    private void render() {
        Graphics g = panel.getGraphics();
        g.drawImage(panel.getImage(), 0, 0, null);
        g.dispose();
    }

    /**
     * Utility method to pause thread execution for a given duration.
     *
     * @param time time to sleep in milliseconds
     */
    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setWeaponFactory(WeaponFactory factory) {
        this.weaponFactory = factory;
    }
}