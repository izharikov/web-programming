package recording.entity;

/**
 * Composition Interface
 */
public interface Composition {
    CompositionDuration getDuration();

    void setDuration(CompositionDuration pDuration);

    String getName();

    void setName(String pNameOfComposition);

    int getYearOfCreation();

    void setYearOfCreation(int pYearOfCreation);

    int getDaysInTopList();

    void setDaysInTopList(int pDaysInTopList);

    void setType(String type);

    String getType();
}
