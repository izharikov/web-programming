package recording.options;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recording.comparator.CompositionComparator;
import recording.comparator.CompositionCompare;
import recording.entity.composition.Composition;
import recording.entity.duration.CompositionDuration;
import recording.exception.RecordingException;
import recording.factory.CompositionFactory;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Options for recording
 */
public class RecordOptions {
    private static final Logger log = LogManager.getLogger(RecordOptions.class);

    private static final CompositionFactory compFactory = new CompositionFactory();

    private List<Composition> mCompositions = new CopyOnWriteArrayList<>();

    /**
     * write collection of composition on disk
     *
     * @param pCompositions collection of compositions
     */
    public void writeOnDisk(Collection<? extends Composition> pCompositions) {
        mCompositions.addAll(pCompositions);
    }

    private void writeOnDiskNonSynchr(Collection<Composition> pCompositions) {
        mCompositions = new ArrayList<>();
        mCompositions.addAll(pCompositions);
    }

    public void writeCompositionOnDisk(Composition composition) {
        mCompositions.add(composition);
    }

    /**
     * compute overall duration of set of compositions
     *
     * @return overall duration
     */
    public CompositionDuration durationOfWrittenOnDisk() throws RecordingException {
        CompositionDuration cd = new CompositionDuration(0);
        for (Composition composition : mCompositions) {
            cd.plus(composition.getDuration());
        }
        return cd;
    }

    /**
     * sort compositions
     *
     * @param pComparator {@link CompositionComparator}
     */
    public void sort(CompositionComparator pComparator) {
        sort(pComparator, false);
    }

    /**
     * sort compositions
     *
     * @param pComparator {@link CompositionComparator}
     * @param reverse     indicate asc or desc mode
     */
    public void sort(CompositionComparator pComparator, boolean reverse) {
        if (reverse) {
            Collections.sort(mCompositions, Collections.reverseOrder(pComparator));
        } else {
            Collections.sort(mCompositions, pComparator);
        }
    }

    /**
     * find Composition by specified params
     *
     * @param pParameters Map of parameters
     * @return finded list of Compositions
     */
    public List<Composition> find(Map<String, Object> pParameters) {
        RecordOptions rec = new RecordOptions();
        List<Composition> result = new LinkedList<>(mCompositions);
        rec.writeOnDiskNonSynchr(result);
        handleParameterRange(rec, pParameters.get("duration"), CompositionCompare.DURATION);
        handleParameterRange(rec, pParameters.get("year"), CompositionCompare.YEAR_OF_CREATION);
        handleParameterRange(rec, pParameters.get("top"), CompositionCompare.DAYS_IN_TOP);
        handleListContains(rec, (List<String>) pParameters.get("type"), "type");
        handleListContains(rec, (List<String>) pParameters.get("name"), "name");
        return new ArrayList<>(rec.mCompositions);
    }

    /**
     * find list of Composition, witch specified {@code field} are in {@code availableParams}
     *
     * @param rec             modified RecordOptions field
     * @param availableParams list of possible values of field
     * @param field           name of field
     */
    private static void handleListContains(RecordOptions rec, List<String> availableParams, String field) {
        if (rec.mCompositions.size() != 0 && availableParams != null && availableParams.size() > 0) {
            List<Composition> result = new ArrayList<>();
            if (field.equalsIgnoreCase("name")) {
                for (Composition c : rec.mCompositions) {
                    if (availableParams.contains(c.getName())) {
                        result.add(c);
                    }
                }
            }
            if (field.equalsIgnoreCase("type")) {
                for (Composition c : rec.mCompositions) {
                    if (availableParams.contains(c.getType())) {
                        result.add(c);
                    }
                }
            }
            rec.mCompositions = result;
        }
    }

    /**
     * filters list of Compositions by {@code param}
     *
     * @param rec   modified RecordOptions field
     * @param param Map, that contains min and max keys
     * @param c     used comporator
     */
    private static void handleParameterRange(RecordOptions rec, Object param, CompositionComparator c) {
        if (rec.mCompositions.size() != 0 && param instanceof Map) {
            Map paramMap = (Map) param;
            Object min = paramMap.get("min");
            Object max = paramMap.get("max");
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

    /**
     * generates key for binary search
     *
     * @param c     comparator, that will be used in binary search
     * @param value value, that will used for search
     * @return Composition object
     */
    private static Composition generateKey(CompositionComparator c, Object value) {
        log.debug("Generation key");
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


    /**
     * executes binary search in mCompositions using {@code comparator}
     *
     * @param key        searched Composition object
     * @param comparator CompositionComparator object
     * @return index of key, or of final element (if there is no key in collection)
     */
    private int binarySearch(Composition key, CompositionComparator comparator) {
        int end = mCompositions.size() - 1;
        if (comparator.compare(key, mCompositions.get(0)) < 0) {
            return 0;
        } else if (comparator.compare(key, mCompositions.get(mCompositions.size() - 1)) > 0) {
            return mCompositions.size();
        }
        return binarySearch(key, 0, end, comparator);
    }

    public List<Composition> getCompositions() {
        return mCompositions;
    }

    /**
     * executes binary search in mCompositions using {@code comparator}
     *
     * @param key        searched Composition object
     * @param start      start index
     * @param end        end index
     * @param comparator CompositionComparator object
     * @return index of key, or of final element (if there is no key in collection)
     */
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
