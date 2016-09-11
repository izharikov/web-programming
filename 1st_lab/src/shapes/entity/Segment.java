package shapes.entity;

/**
 * Created by Igor on 01.09.2016.
 */
public class Segment implements Shape{
    private Point firstEnd;
    private Point secondEnd;

    public Segment(Point firstEnd, Point secondEnd) {
        this.firstEnd = firstEnd;
        this.secondEnd = secondEnd;
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

    public boolean isSameLength(Segment segment) {
        double lenght1 = segment.getFirstEnd().distanceBetweenPoint(segment.getSecondEnd());
        double length2 = getFirstEnd().distanceBetweenPoint(getSecondEnd());
        return lenght1 == length2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Segment)) return false;

        Segment segment = (Segment) o;

        if (firstEnd != null ? !firstEnd.equals(segment.firstEnd) : segment.firstEnd != null) return false;
        return secondEnd != null ? secondEnd.equals(segment.secondEnd) : segment.secondEnd == null;

    }

    @Override
    public int hashCode() {
        int result = firstEnd != null ? firstEnd.hashCode() : 0;
        result = 31 * result + (secondEnd != null ? secondEnd.hashCode() : 0);
        return result;
    }
}
