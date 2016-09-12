package shapes.entity;

import shapes.exception.ShapeException;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Square class<br>
 *     See also: {@link shapes.option.SquareOption}, {@link Segment}, {@link Point}
 */
public class Square implements Shape{
    // center point of square
    private Point mPointCenter;

    // square vertexes
    private Point[] mVertexes;

    //  segments of square
    private Segment[] mSegments;

    //  length of square's side length
    private double mSideLength;

    //  color of square
    private Color mColor;

    //  rotation angles of the vertices
    private double[] mAlphas;

    /**
     * Create object with default Color Black
     * @param pPointCenter center point of square
     * @param pPointVertex one of 4 square vertex
     * @throws ShapeException throws if {@code pPointCenter.equals(pPointVertex)}
     */
    public Square(Point pPointCenter, Point pPointVertex) throws ShapeException{
        this(pPointCenter, pPointVertex, Color.BLACK);
    }

    /**
     * Creates copy of {@code pSquare}
     * @param pSquare source Square object
     * @throws ShapeException
     */
    public Square(Square pSquare) throws ShapeException {
        this(pSquare.getCenter(), pSquare.getVertexes()[0], pSquare.getColor());
    }

    /**
     * create square object
     * @param pPointCenter center point of square
     * @param pPointVertex one of 4 square vertex
     * @param pColor color of square
     * @throws ShapeException
     */
    public Square(Point pPointCenter, Point pPointVertex, Color pColor) throws ShapeException {
        if ( pPointCenter == null || pPointVertex == null || pColor == null){
            throw new ShapeException(new NullPointerException("Params couldn't be null"));
        }
        if (pPointCenter.equals(pPointVertex)) {
            throw new ShapeException("Vertex point couldn't be the same as center point of square");
        }
        mPointCenter = pPointCenter;
        mSideLength = pPointCenter.distanceBetweenPoint(pPointVertex) * Math.sqrt(2);
        mSideLength = check(mSideLength, Math.round(mSideLength));
        initVertexes(pPointVertex);
        mColor = pColor;
    }

    public Point[] getVertexes() {
        return mVertexes;
    }

    public Point getCenter() {
        return mPointCenter;
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color pColor) {
        this.mColor = pColor;
    }

    public Segment[] getSegments() {
        return mSegments;
    }

    @Override
    public String toString() {
        return "Square{" +
                "Point Center=" + mPointCenter +
                ", \n\tVertexes=" + Arrays.toString(mVertexes) +
                ", \n\tSegments=" + Arrays.toString(mSegments) +
                ", \n\tSide Length=" + mSideLength +
                ", \n\tColor=" + mColor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (mPointCenter != null ? !mPointCenter.equals(square.mPointCenter) : square.mPointCenter != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        Point point = mVertexes[0];
        for(Point sqPoint : square.mVertexes){
            if (sqPoint.equals(point)){
                return true;
            }
        }
        return false;

    }

    @Override
    public int hashCode() {
        int result = mPointCenter != null ? mPointCenter.hashCode() : 0;
        result = 31 * result + new HashSet(Arrays.asList(mVertexes)).hashCode();
        return result;
    }

    /**
     * scale square in {@code pScale} times
     * @param pScale double value of scale (more than 0)
     * @throws ShapeException throws when {@code pScale < 0}
     */
    public void scale(double pScale) throws ShapeException {
        if (pScale <= 0) {
            throw new ShapeException(new IllegalArgumentException("Scale couldn't be less or equal 0"));
        }
        mSideLength *= pScale;
        double R = mSideLength / Math.sqrt(2);
        initVertexes(R);
    }

    /**
     * rotate Square object on {@code pAngle} radians
     * @param pAngle angle of rotation (in radians)
     */
    public void rotate(double pAngle) {
        for (int i = 0; i < 4; i++) {
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
        initSegments();
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

    private void initSegments() {
        mSegments = new Segment[4];
        for (int i = 0; i < 4; i++) {
            mSegments[i] = new Segment(mVertexes[i], mVertexes[(i + 1) % 4]);
        }
    }

    /**
     *
     * @param value source value of variable
     * @param rounded rounded value of variable
     * @return {@code rounded}, if it differs very little from <br>{@code value}, value otherwise
     */
    static double check(double value, double rounded) {
        return Math.abs(value - rounded) < 1.0e-15 ? rounded : value;
    }

}
