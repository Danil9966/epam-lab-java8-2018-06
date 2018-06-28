package game.bounceball.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

public class Model implements Externalizable {

    private Ball ball;
    private Field field;
    private Point finish;
    private Set<CheckPoint> checkPoints;


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(ball);
        out.writeObject(field);
        out.writeObject(finish);
        out.writeObject(checkPoints);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ball = (Ball) in.readObject();
        field = (Field) in.readObject();
        finish = (Point) in.readObject();
        checkPoints = (Set<CheckPoint>) in.readObject();
    }
}
