package lab.bus.route;

import lab.bus.core.Pair;
import lab.bus.stop.BusStop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class BusRoute {
    List<Pair<BusStop, Integer>> route;
    private BusStop nextBusStop;
    private String routeNumber;

    public BusRoute(String routeNumber) {
        this.routeNumber = routeNumber;
        route = new ArrayList<>();
    }

    public BusRoute(String routeNumber, List<Pair<BusStop, Integer>> route) {
        this.routeNumber = routeNumber;
        this.route = route;
    }

    public List<Pair<BusStop, Integer>> getRoute() {
        return route;
    }

    public void setRoute(List<Pair<BusStop, Integer>> route) {
        this.route = route;
    }

    public BusStop getNextBusStop() {
        return nextBusStop;
    }

    public void setNextBusStop(BusStop nextBusStop) {
        this.nextBusStop = nextBusStop;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }
}
