package game.bounceball.view;

public interface Graphics {

    void fillRect(int x, int y, int width, int height, int rgb);
    void fillOval(int x, int y, int width, int height, int rgb);
    void drawText(int x, int y, String text);
}
