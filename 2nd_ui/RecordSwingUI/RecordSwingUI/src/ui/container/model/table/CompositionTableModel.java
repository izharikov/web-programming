package ui.container.model.table;

import recording.entity.composition.*;
import recording.entity.duration.CompositionDuration;
import recording.exception.RecordingException;
import recording.options.RecordOptions;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by igor on 9.10.16.
 */
public class CompositionTableModel extends AbstractTableModel {
    private static String[] COLUMN_NAMES = new String[]{"Type", "Name", "Duration", "Days In Top", "Year Of Creat."};
    private List<Composition> mCompositions;

    public CompositionTableModel(List<Composition> pCompositions) {
        mCompositions = pCompositions;
    }

    @Override
    public int getRowCount() {
        return mCompositions.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getParamByIndex(columnIndex, getCompositions().get(rowIndex));
    }

    private Object getParamByIndex(int pColumnIndex, Composition pComposition){
        if ( pComposition != null && pColumnIndex < 5) {
            switch (pColumnIndex) {
                case 0:
                    return pComposition.getType();
                case 1:
                    return pComposition.getName();
                case 2:
                    return pComposition.getDuration();
                case 3:
                    return pComposition.getDaysInTopList();
                case 4:
                    return pComposition.getYearOfCreation();
            }
        }
        return null;
    }

    public List<Composition> getCompositions() {
        return mCompositions;
    }

    public void setCompositions(List<Composition> pCompositions) {
        mCompositions = pCompositions;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    public static CompositionTableModel getTableModelForExample(){
        List<Composition> compositions = new ArrayList<>();
        try {
            CompositionDuration durationA = new CompositionDuration(2, 20);
            Composition compositionA = new RockComposition("Wait and bleed", durationA, 2009, 10);
            CompositionDuration durationB = new CompositionDuration(4, 0);
            Composition compositionB = new PopComposition("B", durationB, 2014, 20);
            CompositionDuration durationC = new CompositionDuration(3, 40);
            Composition compositionC = new ClassicComposition("C", durationC, 1992, 1);
            CompositionDuration durationD = new CompositionDuration(5, 10);
            Composition compositionD = new RapComposition("D", durationD, 2003, 8);
            compositions.add(compositionA);
            compositions.add(compositionB);
            compositions.add(compositionC);
            compositions.add(compositionD);
        }
        catch (RecordingException e){}
        return new CompositionTableModel(compositions);
    }
}
