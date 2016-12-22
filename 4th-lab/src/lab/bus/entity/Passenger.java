package lab.bus.entity;

import lab.bus.event.RunEvent;
import lab.bus.route.PassengerRoute;
import lab.bus.start.DynamicObject;

/**
 * @author Ihar Zharykau
 */
public class Passenger implements DynamicObject {
    private static int ID = 0;
    private String id = "pas" + ID++;
    private PassengerRoute passengerRoute;

    public Passenger(PassengerRoute passengerRoute) {
        this.passengerRoute = passengerRoute;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PassengerRoute getPassengerRoute() {
        return passengerRoute;
    }

    public void setPassengerRoute(PassengerRoute passengerRoute) {
        this.passengerRoute = passengerRoute;
    }

    @Override
    public void start(RunEvent runEvent) {

    }
}
