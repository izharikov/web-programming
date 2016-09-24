package recording.entity;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Igor on 24.09.2016.
 */
public class Composition {
    private String mNameOfComposition;
    private CompositionDuration mDuration;
    private int mYearOfCreation;
    private int mDaysInTopList;

    public Composition(String mNameOfComposition, CompositionDuration mDuration, int mYearOfCreation, int mDaysInTopList) {
        this.mNameOfComposition = mNameOfComposition;
        this.mDuration = mDuration;
        this.mYearOfCreation = mYearOfCreation;
        this.mDaysInTopList = mDaysInTopList;
    }

    public CompositionDuration getDuration() {
        return mDuration;
    }

    public void setDuration(CompositionDuration pDuration) {
        mDuration = pDuration;
    }

    public String getNameOfComposition() {
        return mNameOfComposition;
    }

    public void setNameOfComposition(String pNameOfComposition) {
        mNameOfComposition = pNameOfComposition;
    }

    public int getYearOfCreation() {
        return mYearOfCreation;
    }

    public void setYearOfCreation(int pYearOfCreation) {
        mYearOfCreation = pYearOfCreation;
    }

    public int getDaysInTopList() {
        return mDaysInTopList;
    }

    public void setDaysInTopList(int pDaysInTopList) {
        mDaysInTopList = pDaysInTopList;
    }

    @Override
    public String toString() {
        return "Composition{" +
                "mNameOfComposition='" + mNameOfComposition + "\'\n" +
                ", mDuration = " + mDuration + "\n" +
                ", mYearOfCreation = " + mYearOfCreation + "\n" +
                ", mDaysInTopList = " + mDaysInTopList + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Composition)) return false;

        Composition that = (Composition) o;

        if (mYearOfCreation != that.mYearOfCreation) return false;
        if (mDaysInTopList != that.mDaysInTopList) return false;
        if (mNameOfComposition != null ? !mNameOfComposition.equals(that.mNameOfComposition) : that.mNameOfComposition != null)
            return false;
        return mDuration != null ? mDuration.equals(that.mDuration) : that.mDuration == null;

    }

    @Override
    public int hashCode() {
        int result = mNameOfComposition != null ? mNameOfComposition.hashCode() : 0;
        result = 31 * result + (mDuration != null ? mDuration.hashCode() : 0);
        result = 31 * result + mYearOfCreation;
        result = 31 * result + mDaysInTopList;
        return result;
    }
}
