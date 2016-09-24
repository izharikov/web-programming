package recording.options;

import recording.comparator.CompositionCompare;
import recording.entity.Composition;
import recording.comparator.CompositionComparator;
import recording.entity.CompositionDuration;

import java.util.*;

/**
 * Created by Igor on 24.09.2016.
 */
public class RecordOptions {

    private List<Composition> mCompositions = new LinkedList<>();

    public RecordOptions() {
    }

    public void writeOnDisk(Collection<Composition> pCompositions) {
        mCompositions.addAll(pCompositions);
    }

    public CompositionDuration durationOfWrittenOnDisk() {
        CompositionDuration cd = new CompositionDuration(0);
        for (Composition composition : mCompositions) {
            cd.plus(composition.getDuration());
        }
        return cd;
    }

    public void sort(CompositionComparator pComparator) {
        sort(pComparator, false);
    }

    public void sort(CompositionComparator pComparator, boolean reverse) {
        if (reverse) {
            Collections.sort(mCompositions, Collections.reverseOrder(pComparator));
        } else {
            Collections.sort(mCompositions, pComparator);
        }
    }

    public List<Composition> find(Map<String, Map<String, ? extends Object>> pParameters) {
        List<Composition> result = new LinkedList<>(mCompositions);
        RecordOptions rec = new RecordOptions();
        rec.writeOnDisk(result);
        handleParameter(rec, pParameters.get("duration"), CompositionCompare.DURATION);
        handleParameter(rec, pParameters.get("year"), CompositionCompare.YEAR_OF_CREATION);
        handleParameter(rec, pParameters.get("top"), CompositionCompare.DAYS_IN_TOP);
        return rec.mCompositions;
    }

    private void handleParameter(RecordOptions rec, Map<String, ? extends Object> paramMap, CompositionComparator c){
        if ( rec.mCompositions.size() != 0 && paramMap != null){
            Integer min = (Integer) paramMap.get("min");
            Integer max = (Integer) paramMap.get("max");
            rec.sort(c);
            Composition key = generateKey(c, min);
            int left = rec.find(key, c);
            key = generateKey(c, max);
            int right = rec.find(key, c);
            if ( left < right){
                rec.mCompositions = rec.mCompositions.subList(left, right);
            }
            if ( left == right){
                rec.mCompositions = Collections.EMPTY_LIST;
            }
        }
    }

    private Composition generateKey(CompositionComparator c, Object value){
        Composition result = null;
        if ( c.equals(CompositionCompare.YEAR_OF_CREATION) && value instanceof Integer){
            result = new Composition(null, null, (Integer)value, 0);
        }
        if ( c.equals(CompositionCompare.DAYS_IN_TOP) && value instanceof Integer){
            result = new Composition(null, null, 0, (Integer)value);
        }
        if ( c.equals(CompositionCompare.DURATION) && value instanceof CompositionDuration){
            result = new Composition(null, (CompositionDuration)value, 0, 0);
        }
        if ( c.equals(CompositionCompare.NAME) && value instanceof String){
            result = new Composition((String)value, null, 0, 0);
        }
        return result;
    }

    private void handleDuration(RecordOptions rec,  Map<String, Object> durationParameter){
        if (durationParameter != null) {
            Object minObj = durationParameter.get("min");
            Object maxObj = durationParameter.get("max");
            if (minObj instanceof CompositionDuration && maxObj instanceof CompositionDuration) {
                rec.sort(CompositionCompare.DURATION);
                Composition key = new Composition(null, (CompositionDuration) minObj, 0,0);
                int left = find(key, CompositionCompare.DURATION);
                key = new Composition(null, (CompositionDuration) maxObj, 0,0);
                int right = find(key, CompositionCompare.DURATION);
                if (left < right) {
                    rec.mCompositions = rec.mCompositions.subList(left, right);
                }
            }
        }
    }

    public int find(Composition key, CompositionComparator comparator) {
        int end = mCompositions.size() - 1;
        if (comparator.compare(key, mCompositions.get(0)) < 0) {
            return 0;
        }
        return find(key, 0, end, comparator);
    }

    private int find(Composition key, int start, int end, CompositionComparator comparator) {
        if (end - start <= 1) {
            if (comparator.compare(key, mCompositions.get(start)) == 0) {
                return start;
            }
            return end;
        }
        int middle = (start + end) / 2;
        int res = comparator.compare(key, mCompositions.get(middle));
        if (res == 0) {
            return middle;
        }
        if (res > 0) {
            return find(key, middle, end, comparator);
        } else {
            return find(key, start, middle, comparator);
        }
    }

    @Override
    public String toString() {
        return "RecordOptions{" +
                "Compositions=" + mCompositions +
                '}';
    }
}
