package recording.entity;

/**
 * Composition with type "Pop"
 */
public class PopComposition extends CompositionImpl {
    private String type;

    public PopComposition(String mNameOfComposition, CompositionDuration mDuration, int mYearOfCreation, int mDaysInTopList) {
        super(mNameOfComposition, mDuration, mYearOfCreation, mDaysInTopList);
        type = "Pop";
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
