package shapes.entity;

import shapes.exception.ShapeException;

import java.awt.*;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Igor on 01.09.2016.
 */
public class Square {
    private Point mPointCenter;
    private Point[] mVertexes;
    private double mSideLength;
    private Color mColor;

    public Square(Point pPointCenter, Point pPointVertex) throws ShapeException {
        mPointCenter = pPointCenter;
        if (pPointCenter.equals(pPointVertex)) {
            throw new ShapeException("Vertex point couldn't be the same as center point of square");
        }
        mSideLength = pPointCenter.distanceBetweenPoint(pPointVertex) * Math.sqrt(2);
        mSideLength = check(mSideLength, Math.round(mSideLength));
        initVertexes(pPointVertex);
    }

    public Point[] getVertexes() {
        return mVertexes;
    }

    public Point getCenter() {
        return mPointCenter;
    }

    private void initVertexes(Point pPointVertex) {
        mVertexes = new Point[4];
        mVertexes[0] = pPointVertex;
        double diffX = mPointCenter.getX() - pPointVertex.getX();
        double diffY = mPointCenter.getY() - pPointVertex.getY();
        double alpha = Math.PI / 2;
        double R = mSideLength / Math.sqrt(2);
        if ( diffX != 0){
            alpha = Math.atan(diffY / diffX);
            if ( check(alpha, 0) == 0 && getCenter().getX() > pPointVertex.getX()){
                alpha = -Math.PI;
            }
        }
        else{
            if ( getCenter().getY() > pPointVertex.getY()){
                alpha = -Math.PI / 2;
            }
        }
        for (int i = 1; i < 4; i++) {
            double x = R * check(Math.cos(alpha += Math.PI / 2), 0) + getCenter().getX();
            double y = R * check(Math.sin(alpha), 0) + getCenter().getY();
            mVertexes[i] = new Point(check(x, Math.round(x)),check(y, Math.round(y)));
        }
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

    public static void main(String... args) throws ShapeException{
        Point pointCenter = new Point(0,0);
        Point pointOne = new Point(2,2);
        Square sq = new Square(pointCenter, pointOne);
        System.out.println(sq);
    }

    static double check(double value, double rounded){
        return Math.abs(value -  rounded) < 1.0e-15 ? rounded : value;
    }
}
