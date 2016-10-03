package options;

import com.db.hibernate.entity.CompositionsEntity;
import recording.entity.composition.Composition;
import recording.entity.duration.CompositionDuration;
import recording.exception.RecordingException;
import recording.factory.CompositionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by igor on 3.10.16.
 */
public class CompositionAdapter {
    private static Map<Integer, Integer> idRecover = new HashMap<>();

    public static CompositionsEntity fromComposition(Composition pComposition){
        CompositionsEntity entity = new CompositionsEntity();
        entity.setDaysInTopList(pComposition.getDaysInTopList());
        entity.setDuration(pComposition.getDuration().toString());
        entity.setNameOfComposition(pComposition.getName());
        entity.setYearOfCreation(pComposition.getYearOfCreation());
        entity.setType(pComposition.getType());
        Integer id = idRecover.get(pComposition.hashCode());
        if ( id != null) {
            entity.setId(id);
        }
        return entity;
    }

    public static List<CompositionsEntity> listFromComposition(List<Composition> pListOfComposition){
        List<CompositionsEntity> entities = new ArrayList<>();
        for(Composition composition : pListOfComposition){
            entities.add(fromComposition(composition));
        }
        return entities;
    }

    public static List<Composition> listOfCompositions(List<CompositionsEntity> pEntities){
        List<Composition> compositions = new ArrayList<>();
        for(CompositionsEntity entity : pEntities){
            compositions.add(generateComposition(entity));
        }
        return compositions;
    }

    public static CompositionDuration generateDurationFromString(String pStringDuration) {
        String[] array = pStringDuration.split(" ");
        try {
            CompositionDuration duration = new CompositionDuration(0);
            for (String component : array) {
                int l = component.length();
                if (component.endsWith("s")) {
                    duration.plus(new CompositionDuration(Long.parseLong(component.substring(0, l - 1))));
                } else if (component.endsWith("min")){
                    duration.plus(new CompositionDuration(Long.parseLong(component.substring(0, l - 3)), 0));
                }else if ( component.endsWith("h")){
                    duration.plus(new CompositionDuration(Long.parseLong(component.substring(0, l - 1)), 0, 0));
                }
            }
            return duration;
        } catch (RecordingException e) {
            return null;
        }
    }

    public static Composition generateComposition(CompositionsEntity pEntity){
        CompositionDuration dur = generateDurationFromString(pEntity.getDuration());
        Composition c= new CompositionFactory().getComposition(pEntity.getType(), pEntity.getNameOfComposition(), dur,
                pEntity.getYearOfCreation(), pEntity.getDaysInTopList());
        idRecover.put(c.hashCode(), pEntity.getId());
        return c;
    }
}
