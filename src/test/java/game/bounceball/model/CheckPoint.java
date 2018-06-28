package game.bounceball.model;

import java.util.Scanner;

public class CheckPoint {

    private Point point;
    private boolean checked = false;

    public CheckPoint(Point point) {
        this(point, false);
    }

    private CheckPoint(Point point, boolean checked) {
        this.checked = checked;
        this.point = point;
    }

    public static CheckPoint restore(Scanner input) {
        Point point = Point.restore(input);
        boolean checked = input.nextBoolean();
        return new CheckPoint(point, checked);
    }

    public Point getPoint() {
        return point;
    }

    public boolean isChecked() {
        return checked;
    }
}
