package lab.bus.system;

import lab.bus.core.Pair;
import lab.bus.entity.Bus;
import lab.bus.entity.Passenger;
import lab.bus.event.RunEvent;
import lab.bus.route.BusRoute;
import lab.bus.factory.BusRouteFactory;
import lab.bus.route.PassengerRoute;
import lab.bus.start.DynamicObject;
import lab.bus.stop.BusStop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class BusSystem {
    private static final BusRouteFactory busRouteFactory = new BusRouteFactory();

    private Collection<DynamicObject> dynamicObjects = new ArrayList<>();

    public void init() {
        //  bus stops
        BusStop busStop1 = new BusStop(2);
        BusStop busStop2 = new BusStop(3);
        BusStop busStop3 = new BusStop(4);

        //  bus routes
        Pair p1 = p(busStop1, 10);
        Pair p2 = p(busStop2, 20);
        Pair p3 = p(busStop3, 30);
        List<Pair<BusStop, Integer>> route1 = new ArrayList<>();
        route1.add(p1);
        route1.add(p2);

        List<Pair<BusStop, Integer>> route2 = new ArrayList<>();
        route1.add(p2);
        route1.add(p3);

        BusRoute busRoute10 = busRouteFactory.generate("10", route1);
        BusRoute busRoute20 = busRouteFactory.generate("20", route2);

        // buses
        Bus bus1 = new Bus(busRoute10, 100);
        Bus bus2 = new Bus(busRoute20, 130);

        Pair<Integer, Pair<BusStop, BusStop>> element1 = new Pair<>(10, new Pair<>(busStop1, busStop2));
        Pair<Integer, Pair<BusStop, BusStop>> element2 = new Pair<>(20, new Pair<>(busStop2, busStop3));
        PassengerRoute passengerRoute1 = new PassengerRoute(Arrays.asList(element1));
        PassengerRoute passengerRoute2 = new PassengerRoute(Arrays.asList(element2));
        PassengerRoute passengerRoute3 = new PassengerRoute(Arrays.asList(element1, element2));

        //  passengers
        Passenger passenger1 = new Passenger(passengerRoute1);
        Passenger passenger2 = new Passenger(passengerRoute2);
        Passenger passenger3 = new Passenger(passengerRoute3);

        RunEvent runEvent = new RunEvent();
        dynamicObjects.add(bus1);
        dynamicObjects.add(bus2);
        dynamicObjects.add(passenger1);
        dynamicObjects.add(passenger2);
        dynamicObjects.add(passenger3);
    }

    public void start(){
        RunEvent runEvent = new RunEvent();
        for (DynamicObject dynamicObject : dynamicObjects){
            dynamicObject.start(runEvent);
        }
        runEvent.start();
    }

    public static <T> T[] array(T... args) {
        return args;
    }

    public Pair<BusStop, Integer> p(BusStop first, Integer second){
        return new Pair(first, second);
    }
}
