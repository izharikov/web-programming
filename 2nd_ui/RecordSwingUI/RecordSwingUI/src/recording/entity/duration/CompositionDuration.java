package recording.entity.duration;

import recording.exception.RecordingException;

import java.io.Serializable;
import java.time.Duration;


/**
 * Duration for music composition
 */
public class CompositionDuration implements Comparable<CompositionDuration>, Serializable {
    public static CompositionDuration ZERO_DUR;
    static{
        try {
            ZERO_DUR = new CompositionDuration(0);
        }
        catch (RecordingException e){}
    }
    private Duration mDuration;

    public CompositionDuration(long hours, long minutes, long seconds) throws RecordingException {
        if (hours < 0 || minutes < 0 || seconds < 0) {
            throw new RecordingException(new IllegalArgumentException("h, min or sec can't be less than 0"));
        }
        mDuration = Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    public CompositionDuration(long minutes, long seconds) throws RecordingException {
        this(0, minutes, seconds);
    }

    public CompositionDuration(long seconds) throws RecordingException {
        this(0, seconds);
    }

    public void plus(CompositionDuration pCompoistionDuration) {
        mDuration = mDuration.plus(pCompoistionDuration.mDuration);
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        Duration durToStr = Duration.from(mDuration);
        long value;
        if ((value = durToStr.toHours()) != 0) {
            durToStr = durToStr.minusHours(value);
            strb.append(value + "h ");
        }
        if ((value = durToStr.toMinutes()) != 0) {
            durToStr = durToStr.minusMinutes(value);
            strb.append(value + "min ");
        }
        if ((value = durToStr.getSeconds()) != 0) {
            strb.append(value + "s ");
        }
        return strb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionDuration that = (CompositionDuration) o;

        return mDuration != null ? mDuration.equals(that.mDuration) : that.mDuration == null;

    }

    @Override
    public int hashCode() {
        return mDuration != null ? mDuration.hashCode() : 0;
    }

    @Override
    public int compareTo(CompositionDuration o) {
        return mDuration.compareTo(o.mDuration);
    }

}