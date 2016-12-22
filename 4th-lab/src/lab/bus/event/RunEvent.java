package lab.bus.event;

import java.util.EventObject;

/**
 * @author Ihar Zharykau
 */
public class RunEvent {
    private boolean run = false;

    public RunEvent() {
    }

    public boolean isRun() {
        return run;
    }

    public void start(){
        run = true;
    }
}
