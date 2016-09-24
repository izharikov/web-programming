package recording.options;

import recording.entity.Composition;
import recording.comparator.CompositionComparator;

import java.time.Duration;
import java.util.*;

/**
 * Created by Igor on 24.09.2016.
 */
public class RecordOptions {

    private List<Composition> mCompositions = new LinkedList<>();

    public RecordOptions() {
    }

    public void writeOnDisk(Collection<Composition> pCompositions){
        mCompositions.addAll(pCompositions);
    }

    public Duration durationOfWrittenOnDisk(){
        Duration result = Duration.ZERO;
        for(Composition composition : mCompositions){
            result = result.plus(composition.getDuration());
        }
        return result;
    }

    public void sort(CompositionComparator pComparator){
        Collections.sort(mCompositions, pComparator);
    }

    @Override
    public String toString() {
        return "RecordOptions{" +
                "Compositions=" + mCompositions +
                '}';
    }
}
