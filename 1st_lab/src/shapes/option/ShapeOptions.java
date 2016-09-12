package shapes.option;

import shapes.entity.Shape;
import shapes.exception.ShapeException;

import java.awt.*;

/**
 *  Base interface, that keeps options for shape
 */
public interface ShapeOptions {
    void rotate(double angle);

    void scale(double scale) throws ShapeException;

    void changeColor(Color color);

    Shape getShape();
}
