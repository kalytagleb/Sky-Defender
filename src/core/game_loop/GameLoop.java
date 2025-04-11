package core.game_loop;

import input.Key;
import core.PanelGame;
import model.GameObject;
import model.Player;
import model.enemies.AbstractEnemy;
import model.weapon.AbstractWeapon;
import factory.WeaponFactory;
import service.WaveManager;

import java.awt.*;
import java.util.List;

public class GameLoop {
    private final PanelGame panel;
    private final GameContext context;
    private final GameUpdater updater;
    private final GameRenderer renderer;

    private final int TARGET_TIME = 1000000000 / 60;
    private Thread thread;

    public GameLoop(PanelGame panel, GameContext context) {
        this.panel = panel;
        this.context = context;
        this.updater = new GameUpdater();
        this.renderer = new GameRenderer();
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
        updater.update(context, panel.getWidthValue(), panel.getHeightValue());
    }

    private void draw() {
        Graphics2D g2 = panel.getG2();
        renderer.draw(g2, context, panel.getWidthValue(), panel.getHeightValue());
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

//    public void setWeaponFactory(WeaponFactory factory) {
//        this.weaponFactory = factory;
//    }
}