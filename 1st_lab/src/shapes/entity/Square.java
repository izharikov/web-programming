package shapes.entity;

import shapes.exception.ShapeException;

/**
 * Created by Igor on 01.09.2016.
 */
public class Square {
    private Point leftUpperPoint;
    private Point rightDownPoint;

    public Square(Point leftUpperPoint, Point rightDownPoint) {
        this.leftUpperPoint = leftUpperPoint;
        this.rightDownPoint = rightDownPoint;
    }

    public Square(Segment firstSegment, Segment secondSegment) throws ShapeException{
        if ( !areSegmentsValidSquareSide(firstSegment, secondSegment)){
            throw new ShapeException("Invalid segments for square");
        }

    }

    private static boolean areSegmentsValidSquareSide(Segment segmentOne, Segment segmentSecond){
        boolean isValid = true;
        if (segmentOne.isSameLength(segmentSecond)){
            Segment sgm1 = new Segment(segmentOne.getFirstEnd(), segmentSecond.getFirstEnd());
            Segment sgm2 = new Segment(segmentOne.getFirstEnd(), segmentSecond.getSecondEnd());
            Segment sgm3 = new Segment(segmentOne.getSecondEnd(),segmentSecond.getSecondEnd());
            isValid = sgm1.isSameLength(sgm2) || sgm1.isSameLength(sgm3) || sgm2.isSameLength(sgm3);
        }
        return isValid;
    }
}
