package rmi.client;

import recording.entity.composition.*;
import recording.entity.duration.CompositionDuration;
import rmi.server.RmiRecordOption;

import java.rmi.Naming;
import java.util.*;

/**
 * @author Ihar Zharykau
 */
public class ClientMain {
    public static void main(String... args){
        try {
            CompositionDuration durationA = new CompositionDuration(2, 20);
            Composition compositionA = new RockComposition("Wait and bleed", durationA, 2009, 10);
            CompositionDuration durationB = new CompositionDuration(4, 0);
            Composition compositionB = new PopComposition("B", durationB, 2014, 20);
            CompositionDuration durationC = new CompositionDuration(3, 40);
            Composition compositionC = new ClassicComposition("C", durationC, 1992, 1);
            CompositionDuration durationD = new CompositionDuration(5, 10);
            Composition compositionD = new RapComposition("D", durationD, 2003, 8);
            List<Composition> compositions = new ArrayList<>();
            compositions.add(compositionA);
            compositions.add(compositionB);
            compositions.add(compositionC);
            compositions.add(compositionD);
            // rmi part
            String serverName = "rmi://localhost:8005/recordOption";
            RmiRecordOption recordOptions = (RmiRecordOption) Naming.lookup(serverName);

            //
            recordOptions.writeOnDisk(compositions);

            Map<String, Object> params = new HashMap<>();
            Map<String, Integer> yearParams = new HashMap<>();
            yearParams.put("min", 1993);
            yearParams.put("max", 2010);
            params.put("year", yearParams);
            params.put("type", Collections.singletonList("Rock"));
            System.out.println(recordOptions.find(params));

            System.out.println(recordOptions.durationOfWrittenOnDisk());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
