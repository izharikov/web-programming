package recording.entity;

/**
 * Created by Igor on 25.09.2016.
 */
public interface Composition {
    CompositionDuration getDuration();

    void setDuration(CompositionDuration pDuration) ;

    String getNameOfComposition();

    void setNameOfComposition(String pNameOfComposition);

    int getYearOfCreation();

    void setYearOfCreation(int pYearOfCreation);

    int getDaysInTopList();

    void setDaysInTopList(int pDaysInTopList);

    void setType(String type);

    String getType();
}
