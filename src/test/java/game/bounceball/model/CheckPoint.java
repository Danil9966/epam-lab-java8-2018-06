package game.bounceball.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class CheckPoint implements Externalizable {

    private Point point;
    private boolean checked = false;

    public CheckPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(point);
        out.writeBoolean(checked);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        point = (Point) in.readObject();
        checked = in.readBoolean();
    }
}
