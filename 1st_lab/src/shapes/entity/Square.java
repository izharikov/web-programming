package shapes.entity;

import shapes.exception.ShapeException;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Igor on 01.09.2016.
 */
public class Square {
    private Point mPointCenter;
    private Point[] mVertexes;
    private double mSideLength;
    private Color mColor;
    private double[] mAlphas;

    public Square(Point pPointCenter, Point pPointVertex) throws ShapeException {
        mPointCenter = pPointCenter;
        if (pPointCenter.equals(pPointVertex)) {
            throw new ShapeException("Vertex point couldn't be the same as center point of square");
            //throw new ShapeException(new ArrayIndexOutOfBoundsException());
        }
        mSideLength = pPointCenter.distanceBetweenPoint(pPointVertex) * Math.sqrt(2);
        mSideLength = check(mSideLength, Math.round(mSideLength));
        initVertexes(pPointVertex);
    }

    public Square(Square pSquare) throws ShapeException {
        this(pSquare.getCenter(), pSquare.getVertexes()[0]);
    }

    public Point[] getVertexes() {
        return mVertexes;
    }

    public Point getCenter() {
        return mPointCenter;
    }

    private void initVertexes(Point pPointVertex) {
        mVertexes = new Point[4];
        mAlphas = new double[4];
        mVertexes[0] = pPointVertex;
        mAlphas[0] = computeAngle(pPointVertex);
        for (int i = 1; i < 4; i++) {
            mAlphas[i] = mAlphas[i - 1] + Math.PI / 2;
        }
        double R = mSideLength / Math.sqrt(2);
        initVertexes(R);
    }

    private double computeAngle(Point pPoint) {
        double alpha = Math.PI / 2;
        double diffX = mPointCenter.getX() - pPoint.getX();
        double diffY = mPointCenter.getY() - pPoint.getY();
        if (diffX != 0) {
            alpha = Math.atan(diffY / diffX);
            if (check(alpha, 0) == 0 && getCenter().getX() > pPoint.getX()) {
                alpha = -Math.PI;
            }
        } else {
            if (getCenter().getY() > pPoint.getY()) {
                alpha = -Math.PI / 2;
            }
        }
        return alpha;
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color pColor) {
        this.mColor = pColor;
    }

    @Override
    public String toString() {
        return "Square{" +
                "mPointCenter=" + mPointCenter +
                ", mVertexes=" + Arrays.toString(mVertexes) +
                ", mSideLength=" + mSideLength +
                '}';
    }

    public void scale(double pScale) throws ShapeException {
        if (pScale <= 0) {
            throw new ShapeException(new IllegalArgumentException("Scale couldn't be less or equal 0"));
        }
        mSideLength *= pScale;
        double R = mSideLength / Math.sqrt(2);
        initVertexes(R);
    }

    public void rotate(double pAngle){
        for(int i = 0; i < 4; i++){
            mAlphas[i] += pAngle;
        }
        initVertexes(mSideLength / Math.sqrt(2));
    }

    private void initVertexes(double pRadius) {
        for (int i = 0; i < 4; i++) {
            double x = pRadius * check(Math.cos(mAlphas[i]), 0) + getCenter().getX();
            double y = pRadius * check(Math.sin(mAlphas[i]), 0) + getCenter().getY();
            mVertexes[i] = new Point(check(x, Math.round(x)), check(y, Math.round(y)));
        }
    }

    public static void main(String... args) throws ShapeException {
        Point pointCenter = new Point(1, 1);
        Point pointOne = new Point(2, 2);
        Square sq = new Square(pointCenter, pointOne);
        sq.scale(2);
//        sq.rotate(Math.PI / 2);
        System.out.println(sq);
    }

    static double check(double value, double rounded) {
        return Math.abs(value - rounded) < 1.0e-15 ? rounded : value;
    }
}
