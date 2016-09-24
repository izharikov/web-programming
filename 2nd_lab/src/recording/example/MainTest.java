package recording.example;

import recording.entity.Composition;
import recording.entity.CompositionDuration;
import recording.options.RecordOptions;

import java.util.*;

/**
 * Created by Igor on 24.09.2016.
 */
public class MainTest {
    public static void main(String... args) {
        CompositionDuration durationA = new CompositionDuration(2, 20);
        Composition compositionA = new Composition("A", durationA, 2009, 10);
        CompositionDuration durationB = new CompositionDuration(4, 0);
        Composition compositionB = new Composition("B", durationB, 2014, 20);
        CompositionDuration durationC = new CompositionDuration(3, 40);
        Composition compositionC = new Composition("C", durationC, 1992, 1);
        CompositionDuration durationD = new CompositionDuration(5, 10);
        Composition compositionD = new Composition("D", durationC, 2003, 8);
        List<Composition> compositions = new ArrayList<>();
        compositions.add(compositionA);
        compositions.add(compositionB);
        compositions.add(compositionC);
        compositions.add(compositionD);
        RecordOptions recordOptions = new RecordOptions();
        recordOptions.writeOnDisk(compositions);

        Map<String, Map<String, ? extends Object>> params = new HashMap<>();
        Map<String, Integer> yearParams = new HashMap<>();
        yearParams.put("min", 1993);
        yearParams.put("max", 2010);
        params.put("year", yearParams);
        System.out.println(recordOptions.find(params));

//        Composition key = new Composition(null, null, 2010, 0);
//        recordOptions.sort(CompositionCompare.YEAR_OF_CREATION);
//        System.out.println(recordOptions.find(key, CompositionCompare.YEAR_OF_CREATION));
    }
}
