package core.game_loop;

import core.game_loop.panel.PanelGame;
import service.game_state.IGameState;

import javax.swing.*;
import java.awt.*;

/**
 * Manages the game loop, updating and rendering the game state.
 */
public class GameLoop {
    private final PanelGame panel;
    private final GameContext context;
    private final GameRenderer renderer;
    private final GameUpdater updater;

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;
    private Thread thread;

    /**
     * Constructs the game loop with the specified panel, context, and renderer.
     *
     * @param panel the game panel
     * @param context the game context
     * @param renderer the game renderer
     */
    public GameLoop(PanelGame panel, GameContext context, GameRenderer renderer) {
        this.panel = panel;
        this.context = context;
        this.renderer = renderer;
        this.updater = new GameUpdater();
    }

    /**
     * Starts the game loop in a separate thread.
     */
    public void start() {
        thread = new Thread(() -> {
            System.out.println("GameLoop thread started");

            while (true) {
                IGameState state = context.getGameStateManager().getCurrentState();
                System.out.println("state: " + state);
                System.out.println("Inside loop");

                long startTime = System.nanoTime();

                state.update(context);

                draw();
                render();

                long time = System.nanoTime() - startTime;
                if (time < TARGET_TIME) {
                    sleep((TARGET_TIME - time) / 1000000);
                }
            }
        }, "GameLoop");
        thread.start();
    }

    /**
     * Draws the game state to the off-screen buffer.
     */
    private void draw() {
        System.out.println("Draw called");

        Graphics2D g2 = panel.getG2();
        renderer.draw(g2, context, panel.getWidthValue(), panel.getHeightValue());

//        g2.setColor(Color.RED);
//        g2.fillRect(0, 0, panel.getWidthValue(), panel.getHeightValue());
//
//        System.out.println("Draw called");
    }

    /**
     * Renders the off-screen image to the screen.
     */
    private void render() {
//        Graphics g = panel.getGraphics();
//        g.drawImage(panel.getImage(), 0, 0, null);
//        g.dispose();
        SwingUtilities.invokeLater(panel::repaint);
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

//    public void setWeaponFactory(WeaponFactory main.factory) {
//        this.weaponFactory = main.factory;
//    }
}