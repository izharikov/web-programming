package shapes.option;

import shapes.entity.Square;
import shapes.exception.ShapeException;

import java.awt.*;

/**
 * SquareOption class: provides options for {@link Square}: every method copies source<br>
 *     {@link Square} object and performs some operation<br>
 *         See also: {@link Square}
 */
public class SquareOption{
    /**
     * copies {@code pSquare} object and perform scale transform for it
     * @param pSquare source {@link Square} object
     * @param pScale double value of scale
     * @return scale transformed {@link Square}
     * @throws ShapeException when pSquare == null or pScale is less or equal 0
     */
    public static Square scaleTransform(Square pSquare, double pScale) throws ShapeException{
        Square result = new Square(pSquare);
        result.scale(pScale);
        return result;
    }

    /**
     * copies {@code pSquare} object and perform angle transform for it
     * @param pSquare source {@link Square} object
     * @param pAngle double value of angle transform
     * @return angle transform {@link Square}
     * @throws ShapeException when pSquare == null
     */
    public static Square rotateTransform(Square pSquare, double pAngle) throws ShapeException{
        Square result = new Square(pSquare);
        result.rotate(pAngle);
        return result;
    }

    /**
     * copies {@code pSquare} object and perform color changing
     * @param pSquare source {@link Square} object
     * @param pColor new Color value
     * @return {@link Square} with changed color
     * @throws ShapeException when pSquare == null
     */
    public static Square changeColor(Square pSquare, Color pColor) throws ShapeException{
        Square result = new Square(pSquare);
        result.setColor(pColor);
        return result;
    }
}
