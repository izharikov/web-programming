package shapes.option;

import shapes.entity.Square;
import shapes.exception.ShapeException;

/**
 * Created by igor on 10.9.16.
 */
public class SquareOption {
    public static Square scaleTransform(Square pSquare, double pScale) throws ShapeException{
        Square result = new Square(pSquare);

        return result;
    }
}
