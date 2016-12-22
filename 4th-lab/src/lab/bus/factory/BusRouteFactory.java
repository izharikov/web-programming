package lab.bus.factory;

import lab.bus.core.Pair;
import lab.bus.route.BusRoute;
import lab.bus.stop.BusStop;

import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class BusRouteFactory {
    public BusRoute generate(String busRouteNo, List<Pair<BusStop, Integer>> busStopsAndWaitTime){
        BusRoute busRoute = new BusRoute(busRouteNo);
        busRoute.setRoute(busStopsAndWaitTime);
        return busRoute;
    }
}
