package recording.entity.composition;

import recording.entity.duration.CompositionDuration;

/**
 * Composition with type "Rap"
 */
public class RapComposition extends Composition {
    private String type;

    public RapComposition(String mNameOfComposition, CompositionDuration mDuration, int mYearOfCreation, int mDaysInTopList) {
        super(mNameOfComposition, mDuration, mYearOfCreation, mDaysInTopList);
        type = "Rap";
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public RapComposition() {
        super();
    }

    @Override
    public String getType() {
        return type;
    }
}
