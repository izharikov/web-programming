package lab.bus.route;

import lab.bus.core.Pair;
import lab.bus.entity.Bus;
import lab.bus.stop.BusStop;

import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class PassengerRoute {
    List<Pair<Integer, Pair<BusStop, BusStop>>> route;

    public PassengerRoute(List<Pair<Integer, Pair<BusStop, BusStop>>> route) {
        this.route = route;
    }

    public List<Pair<Integer, Pair<BusStop, BusStop>>> getRoute() {
        return route;
    }

    public void setRoute(List<Pair<Integer, Pair<BusStop, BusStop>>> route) {
        this.route = route;
    }
}
