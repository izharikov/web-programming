package recording.comparator;

import recording.entity.Composition;

/**
 * Created by Igor on 24.09.2016.
 */
public class CompositionCompare {
    public static final CompositionComparator NAME = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getNameOfComposition().compareTo(o2.getNameOfComposition());
        }
    };

    public static final CompositionComparator DURATION = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getDuration().compareTo(o2.getDuration());
        }
    };

    public static final CompositionComparator YEAR_OF_CREATION = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getYearOfCreation() - o2.getYearOfCreation();
        }
    };

    public static final CompositionComparator DAYS_IN_TOP = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getDaysInTopList() - o2.getDaysInTopList();
        }
    };

    public static final CompositionComparator TYPE = new CompositionComparator() {
        @Override
        public int compare(Composition o1, Composition o2) {
            return o1.getType().compareTo(o2.getType());
        }
    };
}
