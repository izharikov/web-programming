package shapes.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Segment class
 */
public class Segment implements Shape{
    private Point firstEnd;
    private Point secondEnd;
    private double mLength;

    /**
     *
     * @param firstEnd first end of Segment
     * @param secondEnd second end of Segment
     */
    public Segment(Point firstEnd, Point secondEnd) {
        this.firstEnd = firstEnd;
        this.secondEnd = secondEnd;
        mLength = firstEnd.distanceBetweenPoint(secondEnd);
    }

    /**
     *
     * @param firstEnd first end of Segment
     * @param angle angle between Ox and segment
     * @param length length of Segment
     */
    public Segment(Point firstEnd, double angle, double length){
        mLength = length;
        this.firstEnd = firstEnd;
        double x = length * Math.cos(angle) + firstEnd.getX();
        double y = length * Math.sin(angle) + firstEnd.getY();
        secondEnd = new Point(x,y);
    }

    public Point getFirstEnd() {
        return firstEnd;
    }

    public void setFirstEnd(Point firstEnd) {
        this.firstEnd = firstEnd;
    }

    public Point getSecondEnd() {
        return secondEnd;
    }

    public void setSecondEnd(Point secondEnd) {
        this.secondEnd = secondEnd;
    }

    public double getLength(){
        return mLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Segment)) return false;

        Segment segment = (Segment) o;

        if (firstEnd != null ? !firstEnd.equals(segment.firstEnd) : segment.firstEnd != null) return false;
        boolean res = secondEnd != null ? secondEnd.equals(segment.secondEnd) : segment.secondEnd == null;
        if ( !res){
            res = secondEnd != null ? secondEnd.equals(segment.firstEnd) : segment.secondEnd == null;
            res = res && firstEnd.equals(segment.secondEnd);
        }
        return res;
    }

    @Override
    public int hashCode() {
        Set set =  new HashSet<>();
        set.add(firstEnd);
        set.add(secondEnd);
        return set.hashCode();
    }

    @Override
    public String toString() {
        return "Segment{" +
                "\n\t\tfirstEnd=" + firstEnd +
                ", \n\t\tsecondEnd=" + secondEnd +
                ", \n\t\tLength=" + mLength +
                '}';
    }

}
