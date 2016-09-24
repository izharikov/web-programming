package recording.entity;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Igor on 24.09.2016.
 */
public class Composition {
    private String mNameOfComposition;
    private Duration mDuration;
    private int mYearOfCreation;
    private int mDaysInTopList;

    public Duration getDuration() {
        return mDuration;
    }

    public void setDuration(Duration pDuration) {
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
}
