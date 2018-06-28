package game.bounceball.model;

import java.io.*;
import java.util.Scanner;

public class Field {

    private final Sector[][] field;

    private Field(Sector[][] field) {
        this.field = field;
    }

    /**
     *   0 1 2 3 4 Y
     * 0 . . . . .
     * 1 . . . . .
     * 2 . . w w .
     * 3 . . . w .
     * 4 . . . w .
     * 5 . . . w .
     * X
     */
    // TODO подумать над выделением в отдельный класс (стратегия?)
    public static Field restore(Scanner input) {
        int numRows = input.nextInt();
        int numCollumns = input.nextInt();

        Sector[][] field = new Sector[numRows][numCollumns];
        for (int x = 0; x < numRows; ++x) {
            for (int y = 0; y < numCollumns; ++y) {
                field[x][y] = input.nextInt() == 1 ? Sector.WALL : Sector.GROUND;
            }
        }
        return new Field(field);
    }

    public static void save(OutputStream output) {

    }

    public enum Sector {
        WALL,
        GROUND
    }
}
