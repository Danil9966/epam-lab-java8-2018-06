package game.bounceball.model;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Model {

    private Field field;
    private Point finish;
    private Point ball;
    private Set<CheckPoint> checkPoints;

    private Model(Field field, Point finish, Point ball, Set<CheckPoint> checkPoints) {
        this.field = field;
        this.finish = finish;
        this.ball = ball;
        this.checkPoints = checkPoints;
    }

    public static Model restore(Scanner input) {
        Field field = Field.restore(input);
        Point finish = Point.restore(input);
        Point ball = Point.restore(input);

        Set<CheckPoint> checkPoints = new HashSet<>();
        int numberCheckPoints = input.nextInt();
        for (int i = 0; i < numberCheckPoints; ++i) {
            checkPoints.add(CheckPoint.restore(input));
        }

        return new Model(field, finish, ball, checkPoints);
    }
}
