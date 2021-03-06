package recording.factory;

import recording.entity.composition.*;
import recording.entity.duration.CompositionDuration;

/**
 * Class, that creates compositions depends on type of it
 */
public class CompositionFactory {
    public Composition getComposition(String type, String pName, CompositionDuration pDur, int pYear, int pDays) {
        if (type.equalsIgnoreCase("rock")) {
            return new RockComposition(pName, pDur, pYear, pDays);
        }
        if (type.equalsIgnoreCase("classic")) {
            return new ClassicComposition(pName, pDur, pYear, pDays);
        }
        if (type.equalsIgnoreCase("pop")) {
            return new PopComposition(pName, pDur, pYear, pDays);
        }
        if (type.equalsIgnoreCase("rap")) {
            return new RapComposition(pName, pDur, pYear, pDays);
        }
        return null;
    }

    private CompositionFactory(){}

    private static final CompositionFactory instance = new CompositionFactory();

    public static CompositionFactory instance(){
        return instance;
    }
}
