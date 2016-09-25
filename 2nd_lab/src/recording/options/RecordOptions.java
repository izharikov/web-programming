package recording.options;

import recording.comparator.CompositionCompare;
import recording.entity.Composition;
import recording.comparator.CompositionComparator;
import recording.entity.CompositionDuration;
import recording.factory.CompositionFactory;

import java.util.*;

/**
 * Created by Igor on 24.09.2016.
 */
public class RecordOptions {
    private static final CompositionFactory compFactory = new CompositionFactory();
    private List<Composition> mCompositions = new LinkedList<>();

    public RecordOptions() {
    }

    public void writeOnDisk(Collection<? extends Composition> pCompositions) {
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

    public List<? extends Composition> find(Map<String, Object> pParameters) {
        List<Composition> result = new LinkedList<>(mCompositions);
        RecordOptions rec = new RecordOptions();
        rec.writeOnDisk(result);
        handleParameter(rec, pParameters.get("duration"), CompositionCompare.DURATION);
        handleParameter(rec, pParameters.get("year"), CompositionCompare.YEAR_OF_CREATION);
        handleParameter(rec, pParameters.get("top"), CompositionCompare.DAYS_IN_TOP);
        handleEquals(rec, (List<String>)pParameters.get("type"), "type");
        handleEquals(rec, (List<String>)pParameters.get("name"), "name");
        return rec.mCompositions;
    }

    private void handleEquals(RecordOptions rec, List<String> availableParams, String field){
        if (rec.mCompositions.size() != 0 &&  availableParams != null && availableParams.size()> 0){
            List<Composition> result = new ArrayList<>();
            if ( field.equalsIgnoreCase("name")){
                for(Composition c : rec.mCompositions){
                    if ( availableParams.contains(c.getNameOfComposition())) {
                        result.add(c);
                    }
                }
            }
            if ( field.equalsIgnoreCase("type")){
                for(Composition c : rec.mCompositions){
                    if ( availableParams.contains(c.getType())) {
                        result.add(c);
                    }
                }
            }
            rec.mCompositions = result;
        }
    }

    private void handleParameter(RecordOptions rec, Object param, CompositionComparator c) {
        if (rec.mCompositions.size() != 0 && param instanceof Map) {
            Map paramMap = (Map)param;
            Integer min = (Integer) paramMap.get("min");
            Integer max = (Integer) paramMap.get("max");
            rec.sort(c);
            Composition key = generateKey(c, min);
            int left = rec.binarySearch(key, c);
            key = generateKey(c, max);
            int right = rec.binarySearch(key, c);
            if (left < right) {
                rec.mCompositions = rec.mCompositions.subList(left, right);
            }
            if (left == right) {
                rec.mCompositions = Collections.EMPTY_LIST;
            }
        }
    }

    private Composition generateKey(CompositionComparator c, Object value) {
        Composition result = null;
        if (c.equals(CompositionCompare.YEAR_OF_CREATION) && value instanceof Integer) {
            result = compFactory.getComposition("rock", null, null, (Integer) value, 0);
        }
        if (c.equals(CompositionCompare.DAYS_IN_TOP) && value instanceof Integer) {
            result = compFactory.getComposition("rock", null, null, 0, (Integer) value);
        }
        if (c.equals(CompositionCompare.DURATION) && value instanceof CompositionDuration) {
            result = compFactory.getComposition("rock", null, (CompositionDuration) value, 0, 0);
        }
        if (c.equals(CompositionCompare.NAME) && value instanceof String) {
            result = compFactory.getComposition("rock", (String) value, null, 0, 0);
        }
        return result;
    }


    private int binarySearch(Composition key, CompositionComparator comparator) {
        int end = mCompositions.size() - 1;
        if (comparator.compare(key, mCompositions.get(0)) < 0) {
            return 0;
        }
        return binarySearch(key, 0, end, comparator);
    }

    private int binarySearch(Composition key, int start, int end, CompositionComparator comparator) {
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
            return binarySearch(key, middle, end, comparator);
        } else {
            return binarySearch(key, start, middle, comparator);
        }
    }

    @Override
    public String toString() {
        return "RecordOptions{" +
                "Compositions=" + mCompositions +
                '}';
    }
}
