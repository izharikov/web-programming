package shapes.test;

import shapes.entity.Point;
import shapes.entity.Shape;
import shapes.entity.Square;
import shapes.exception.ShapeException;
import shapes.option.SquareOption;

/**
 * Created by igor on 11.9.16.
 */
public class SquareTest {
    public static void main(String... args) throws ShapeException{
        Point firstPoint = new Point(0,0);
        Point secondPoint = new Point(5,5);

        Square sq = new Square(firstPoint, secondPoint);
        System.out.println(sq);

        Square sqRotated = SquareOption.rotateTransform(sq, Math.PI / 2);
        System.out.println(sqRotated);

        Shape sqAngled = SquareOption.scaleTransform(sqRotated, 2);
        System.out.println(sqAngled);
    }
}
