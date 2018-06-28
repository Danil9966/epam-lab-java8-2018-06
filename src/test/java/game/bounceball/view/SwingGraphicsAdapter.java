package game.bounceball.view;

import java.awt.*;

public class SwingGraphicsAdapter implements Graphics {

    private final Graphics2D graphics;

    public SwingGraphicsAdapter(Graphics2D graphics) {
        this.graphics = graphics;
    }

    @Override
    public void fillRect(int x, int y, int width, int height, int rgb) {
        graphics.setColor(new Color(rgb));
        graphics.fillRect(x, y, width, height);
    }

    @Override
    public void fillOval(int x, int y, int width, int height, int rgb) {
        graphics.setColor(new Color(rgb));
        graphics.fillOval(x, y, width, height);
    }

    @Override
    public void drawText(int x, int y, String text) {
        char[] symbols = text.toCharArray();
        graphics.drawChars(symbols, 0, symbols.length, x, y);
    }
}
