package game.bounceball.model;

import java.io.*;
import java.util.Scanner;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point restore(Scanner input) {
        int x = input.nextInt();
        int y = input.nextInt();
        return new Point(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
