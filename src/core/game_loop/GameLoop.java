package core.game_loop;

import core.panel.PanelGame;

import java.awt.*;

public class GameLoop {
    private final PanelGame panel;
    private final GameContext context;
    private final GameRenderer renderer;
    private final GameUpdater updater;

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;
    private Thread thread;

    public GameLoop(PanelGame panel, GameContext context, GameRenderer renderer) {
        this.panel = panel;
        this.context = context;
        this.renderer = renderer;
        this.updater = new GameUpdater();
    }

    public void start() {
        thread = new Thread(() -> {
            while (context.getGameStateManager().getCurrentState() != null) {
                long startTime = System.nanoTime();

                context.getGameStateManager().getCurrentState().update(context);

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

    private void draw() {
//        Graphics2D g2 = panel.getG2();
//        renderer.draw(g2, context, panel.getWidthValue(), panel.getHeightValue());

        Graphics2D g2 = panel.getG2();
        renderer.draw(g2, context, panel.getWidthValue(), panel.getHeightValue());

//        g2.setColor(Color.RED);
//        g2.fillRect(0, 0, panel.getWidthValue(), panel.getHeightValue());
//
//        System.out.println("Draw called");
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