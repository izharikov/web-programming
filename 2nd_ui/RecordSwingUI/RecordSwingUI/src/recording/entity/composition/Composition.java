package recording.entity.composition;

import recording.entity.duration.CompositionDuration;

import java.io.Serializable;

/**
 * Base implementation of Composition interface
 */
public abstract class Composition implements Serializable{
    private String mNameOfComposition;
    private CompositionDuration mDuration;
    private int mYearOfCreation;
    private int mDaysInTopList;
    protected String type;

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

    public String getName() {
        return mNameOfComposition;
    }

    public void setName(String pNameOfComposition) {
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

    public abstract String getType() ;

    public abstract void setType(String type);
    @Override
    public String toString() {
        return getType() + "Composition{" +
                "\n\tNameOfComposition='" + mNameOfComposition + "\',\n" +
                "\tDuration = " + mDuration + ",\n" +
                "\tYearOfCreation = " + mYearOfCreation + ".\n" +
                "\tDaysInTopList = " + mDaysInTopList + "\n" +
                "\tType = " + getType() + "\n" +
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
