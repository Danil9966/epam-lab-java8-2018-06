package game.bounceball.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Field implements Externalizable  {

    private Sector[][] field;

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
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        int numRows = field.length;
        out.writeInt(numRows);

        int numCollumns = field[0].length;
        out.writeInt(numCollumns);

        for (Sector[] row : field) {
            for (Sector sector : row) {
                out.write(sector == Sector.WALL ? 1 : 0);

            }
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        int numRows = in.readInt();
        int numCollumns = in.readInt();
        Sector[][] sectors = new Sector[numRows][numCollumns];
        for (int x = 0; x < numRows; ++x) {
            for (int y = 0; y < numCollumns; ++y) {
                sectors[x][y] = in.read() == 1 ? Sector.WALL : Sector.GROUND;
            }
        }
        field = sectors;
    }

    public enum Sector {
        WALL,
        GROUND
    }
}
