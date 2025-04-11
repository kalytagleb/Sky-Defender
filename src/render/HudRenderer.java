package render;

import core.game_loop.GameContext;

import java.awt.*;

public class HudRenderer {
    public void draw(Graphics2D g2, GameContext context) {
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + context.getScore(), 20, 30);

        int maxHP = 100;
        int hp = context.getPlayer().getHp();
        int barWidth = 200;
        int barHeight = 16;
        int padding = 20;

        int currentWidth = (int) (barWidth * (hp / (float) maxHP));
        int barX = 1366 - barWidth - padding;
        int barY = 30 - barHeight / 2;

        g2.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g2.getFontMetrics();
        int labelWidth = fm.stringWidth("HP:");
        g2.drawString("HP:", barX - labelWidth - 10, barY + barHeight - 2);

        g2.setColor(new Color(60, 60, 60));
        g2.fillRoundRect(barX, barY, barWidth, barHeight, 10, 10);

        Color barColor = hp >= 70 ? new Color(0, 200, 0)
                        : hp >= 30 ? new Color(255, 200, 0)
                                   : new Color(200, 0, 0);

        g2.setColor(barColor);
        g2.fillRoundRect(barX, barY, currentWidth, barHeight, 10, 10);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(barX, barY, barWidth, barHeight, 10, 10);
    }
}