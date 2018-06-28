package game.bounceball.swing;

import game.bounceball.view.Graphics;

import javax.swing.*;
import java.awt.*;

public class SwingGraphicsAdapter implements Graphics {

    private final JFrame mainFrame;
    private final Graphics2D graphics;

    public SwingGraphicsAdapter(JFrame mainFrame, Graphics2D graphics) {
        this.mainFrame = mainFrame;
        this.graphics = graphics;
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public void drawRect(int x, int y, int width, int height, int rgb) {
        graphics.setColor(new Color(rgb));
        graphics.fillRect(y, x, width, height);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public void drawOval(int x, int y, int width, int height, int rgb) {
        graphics.setColor(new Color(rgb));
        graphics.fillOval(y, x, width, height);
    }

    @Override
    public void drawText(int x, int y, String text, int rgb) {
        char[] symbols = text.toCharArray();
        graphics.setColor(new Color(rgb));
        graphics.drawChars(symbols, 0, symbols.length, x, y);
    }

    @Override
    public void showCongratsDialog() {
        JOptionPane.showConfirmDialog(mainFrame, "You are winner!", "Congratulations", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showNeedMoreBonusesDialog() {
        JOptionPane.showConfirmDialog(mainFrame, "Need to take all bonuses to win!", "Information", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}
