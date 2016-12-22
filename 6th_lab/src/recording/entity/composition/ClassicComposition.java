package recording.entity.composition;

import recording.entity.duration.CompositionDuration;

/**
 * Composition with type "Classic"
 */
public class ClassicComposition extends Composition {
    private String type;

    public ClassicComposition(String mNameOfComposition, CompositionDuration mDuration, int mYearOfCreation, int mDaysInTopList) {
        super(mNameOfComposition, mDuration, mYearOfCreation, mDaysInTopList);
        type = "Classic";
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public ClassicComposition() {
        super();
    }

    @Override
    public String getType() {
        return type;
    }
}
