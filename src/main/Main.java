package main;

import component.PanelGame;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame {
    public Main() {
        init();
    }

    public void init() {
        setTitle("Sky Defender");
        setSize(1366, 768);
        setLocationRelativeTo(null); // In order to our main screen was on the center
        setResizable(false); // User can't resize game screen while playing
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // JFrame calls it by default, it's just for readability
        PanelGame panelGame = new PanelGame();
        add(panelGame);
        // method, which allows to intercept window events
        addWindowListener(new WindowAdapter() {
            // it calls, when window is opened first time
            // it helps to
            @Override
            public void windowOpened(WindowEvent e) {
                panelGame.start();
            }
        });
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
    }
}