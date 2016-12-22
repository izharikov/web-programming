package adapter;

import recording.entity.composition.Composition;
import recording.entity.duration.CompositionDuration;
import recording.exception.RecordingException;

import javax.swing.*;

/**
 * Created by igor on 9.10.16.
 */
public class CompositionAdapter {
    public static CompositionDuration generateDurationFromString(String pStringDuration) {
        String[] array = pStringDuration.split(" ");
        try {
            CompositionDuration duration = new CompositionDuration(0,0,0);
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

    private static String formatStr(String str){
        return str.length() == 0 ? "0" : str;
    }

    public static String durationFromUiComponents(JTextField jSecF, JTextField jMinF, JTextField jHoursF){
        String sec = formatStr(jSecF.getText());
        String min = formatStr(jMinF.getText());
        String hour = formatStr(jHoursF.getText());
        return sec + "s " + min + "min " + hour + "h";
    }

    public static Object[] compositionToArray(Composition composition){
        return new Object[]{composition.getType(), composition.getName(), composition.getDuration().toString(),
            composition.getYearOfCreation(), composition.getDaysInTopList()};
    }
}
