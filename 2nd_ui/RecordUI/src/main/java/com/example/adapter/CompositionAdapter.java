package com.example.adapter;

import com.db.hibernate.entity.CompositionsEntity;
import recording.entity.composition.Composition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igor on 3.10.16.
 */
public class CompositionAdapter {
    public static CompositionsEntity fromComposition(Composition pComposition){
        CompositionsEntity entity = new CompositionsEntity();
        entity.setDaysInTopList(pComposition.getDaysInTopList());
        entity.setDuration(pComposition.getDuration().toString());
        entity.setNameOfComposition(pComposition.getName());
        entity.setYearOfCreation(pComposition.getYearOfCreation());
        entity.setType(pComposition.getType());
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
            compositions.add(entity.generateComposition());
        }
        return compositions;
    }
}
