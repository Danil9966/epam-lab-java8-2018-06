package game.bounceball.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Ball implements Externalizable {

    private int health;
    private Point position;

    public int getHealth() {
        return health;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(health);
        out.writeObject(position);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        health = in.readInt();
        position = (Point) in.readObject();
    }
}
