package shapes.test;

import shapes.entity.Point;
import shapes.entity.Square;
import shapes.exception.ShapeException;
import shapes.option.ShapeOptions;


import java.awt.*;

/**
 * Created by igor on 11.9.16.
 */
public class SquareTest {
    public static void main(String... args) throws ShapeException{
        Point firstPoint = new Point(0,0);
        Point secondPoint = new Point(Math.sqrt(2),0);

        Square sq = new Square(firstPoint, secondPoint);
        System.out.println(sq);

        ShapeOptions squareOptions = sq.getSquareOptions();

        squareOptions.rotate(Math.PI / 4);
        System.out.println(squareOptions.getShape());

        squareOptions.scale(2);
        System.out.println(squareOptions.getShape());

        squareOptions.changeColor(Color.RED);
        System.out.println(squareOptions.getShape());;
    }
}
