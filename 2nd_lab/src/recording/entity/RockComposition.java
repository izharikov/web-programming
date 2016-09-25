package recording.entity;

/**
 * Created by Igor on 25.09.2016.
 */
public class RockComposition extends CompositionImpl {
    private String type;

    public RockComposition(String mNameOfComposition, CompositionDuration mDuration, int mYearOfCreation, int mDaysInTopList) {
        super(mNameOfComposition, mDuration, mYearOfCreation, mDaysInTopList);
        type = "Rock";
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
