package lab.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import recording.entity.composition.Composition;
import recording.entity.duration.CompositionDuration;
import recording.exception.RecordingException;
import recording.factory.CompositionFactory;

import javax.xml.stream.XMLStreamReader;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class CompositionsBaseXMLUtils {
    private static final String COMPOSITION_TYPE = "type";
    private static final String COMPOSITION_NAME = "name";
    private static final String COMPOSITION_YEAR = "year";
    private static final String COMPOSITION_DAYS_IN_TOP = "days-in-top";


    private static final Logger log = LogManager.getLogger(CompositionsBaseXMLUtils.class);
    /**
     * parsed compositions
     */
    private List<Composition> resultCompositionList;

    /**
     * factory to create compositions
     */
    private CompositionFactory compositionFactory = CompositionFactory.instance();

    //  Values of one composition properties
    private String type;
    private String name;
    private int year;
    private int daysInTop;
    private CompositionDuration duration;
    private long sec, mins, hours;

    /**
     * current name of tag
     */
    private String currentName;

    /**
     * set default values for variables
     */
    public void initDefaultValues() {
        sec = mins = hours = 0;
        type = name = "";
        year = daysInTop = 0;
        try {
            duration = new CompositionDuration(0);
        } catch (RecordingException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * compute and set value of duration object
     * @param attributes xml attributes of element
     */
    public void initDuration(Attributes attributes) {
        String hoursString = attributes.getValue("hours");
        String minsString = attributes.getValue("min");
        String secsString = attributes.getValue("sec");
        if (hoursString != null) {
            hours = Long.parseLong(hoursString);
        }
        if (minsString != null) {
            mins = Long.parseLong(minsString);
        }
        if (secsString != null) {
            sec = Long.parseLong(secsString);
        }
        try {
            duration = new CompositionDuration(hours, mins, sec);
        } catch (RecordingException e) {
            log.error(e.getMessage(), e);
        }
        log.debug("set duration : " + duration);
    }

    public void initDuration(XMLStreamReader reader){
        String hoursString = reader.getAttributeValue(null, "hours");
        String minsString = reader.getAttributeValue(null, "min");
        String secsString = reader.getAttributeValue(null, "sec");
        if (hoursString != null) {
            hours = Long.parseLong(hoursString);
        }
        if (minsString != null) {
            mins = Long.parseLong(minsString);
        }
        if (secsString != null) {
            sec = Long.parseLong(secsString);
        }
        try {
            duration = new CompositionDuration(hours, mins, sec);
        } catch (RecordingException e) {
            log.error(e.getMessage(), e);
        }
        log.debug("set duration : " + duration);
    }

    public void setSomeValue(String currentName, String value){
        if ( value == null){
            return;
        }
        value = value.trim();
        if ( value.length() == 0){
            return;
        }
        switch (currentName) {
            case COMPOSITION_NAME:
                name = value;
                break;
            case COMPOSITION_DAYS_IN_TOP:
                daysInTop = Integer.parseInt(value);
                break;
            case COMPOSITION_TYPE:
                type = value;
                break;
            case COMPOSITION_YEAR:
                year = Integer.parseInt(value);
                break;
            default:
                return;
        }
    }

    public Composition createComposition(){
        return compositionFactory.getComposition(type, name, duration, year, daysInTop);
    }
}
