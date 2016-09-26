package recording.comparator;

import recording.entity.composition.Composition;

/**
 * Class, that keeps many comparators for composition interface
 */
public class CompositionCompare {
    /**
     * compare compositions by name
     */
    public static final CompositionComparator NAME = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    /**
     * compare compositions by duration
     */
    public static final CompositionComparator DURATION = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getDuration().compareTo(o2.getDuration());
        }
    };

    /**
     * compare compositions by year of creation
     */
    public static final CompositionComparator YEAR_OF_CREATION = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getYearOfCreation() - o2.getYearOfCreation();
        }
    };

    /**
     * compare compositions by days in top
     */
    public static final CompositionComparator DAYS_IN_TOP = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getDaysInTopList() - o2.getDaysInTopList();
        }
    };

    /**
     * compare compositions by type
     */
    public static final CompositionComparator TYPE = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getType().compareTo(o2.getType());
        }
    };
}
