package recording.entity;

/**
 * Created by Igor on 24.09.2016.
 */
public abstract class CompositionImpl implements Composition {
    private String mNameOfComposition;
    private CompositionDuration mDuration;
    private int mYearOfCreation;
    private int mDaysInTopList;

    public CompositionImpl(String mNameOfComposition, CompositionDuration mDuration, int mYearOfCreation, int mDaysInTopList) {
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
        String modifiedType = getType().substring(0,1).toUpperCase();
        return modifiedType + getType().substring(1) + "Composition{" +
                "mNameOfComposition='" + mNameOfComposition + "\'\n" +
                ", mDuration = " + mDuration + "\n" +
                ", mYearOfCreation = " + mYearOfCreation + "\n" +
                ", mDaysInTopList = " + mDaysInTopList + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositionImpl)) return false;

        CompositionImpl that = (CompositionImpl) o;

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
