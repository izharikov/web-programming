package com.example.controllers;


import com.db.hibernate.entity.CompositionsEntity;
import com.db.hibernate.options.DBCompositionFactory;
import com.example.RecordUiApplication;
import options.CompositionAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import recording.comparator.CompositionComparator;
import recording.comparator.CompositionCompare;
import recording.entity.composition.Composition;
import recording.options.RecordOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by igor on 3.10.16.
 */
@RestController
public class RecordingController{

    private static final DBCompositionFactory dbFactory = RecordUiApplication.getDbCompositionFactory();
    private static final RecordOptions RECORD_OPTIONS = new RecordOptions();


    @RequestMapping("/rest/get-all-compositions")
    public List<CompositionsEntity> getAllCompositions(){
        List<CompositionsEntity> compositions = dbFactory.getAllCompositionEntities();
        return compositions;
    }

    @RequestMapping("/rest/sort")
    public List<CompositionsEntity> sortByCriteria(@RequestParam(value="criteria", required = true) String pCriteria){
        List<CompositionsEntity> entities = getAllCompositions();
        List<Composition> compositions = CompositionAdapter.listOfCompositions(entities);
        RecordOptions recordOptions = new RecordOptions();
        recordOptions.writeOnDisk(compositions);
        CompositionComparator compositionComparator = comparatorMap.get(pCriteria.toLowerCase());
        if ( compositionComparator != null){
            recordOptions.sort(compositionComparator);
        }
        return CompositionAdapter.listFromComposition(recordOptions.getCompositions());
    }

    private static final Map<String, CompositionComparator> comparatorMap = new HashMap<>();
    static{
        comparatorMap.put("type", CompositionCompare.TYPE);
        comparatorMap.put("top", CompositionCompare.DAYS_IN_TOP);
        comparatorMap.put("name", CompositionCompare.NAME);
        comparatorMap.put("duration", CompositionCompare.DURATION);
        comparatorMap.put("year", CompositionCompare.YEAR_OF_CREATION);
    }
}
