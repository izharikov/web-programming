package lab.bus.entity;

import lab.bus.core.Pair;
import lab.bus.route.BusRoute;
import lab.bus.stop.BusStop;

import java.util.concurrent.Semaphore;

/**
 * @author Ihar Zharykau
 */
public class Bus extends Thread {
    private static final int DRIVING_TIME = 100;
    private static int ID = 0;
    private String busId = "bus" + ID++;
    private String busNumber;
    private int countOfPassengers;
    private int maxCountOfPassengers;
    private BusRoute busRoute;

    private Semaphore semaphore;
    private boolean run;

    public Bus(BusRoute busRoute, int countOfPassengers, int maxCountOfPassengers) {
        this.busRoute = busRoute;
        busNumber = busRoute.getRouteNumber();
        this.countOfPassengers = countOfPassengers;
        this.maxCountOfPassengers = maxCountOfPassengers;
        semaphore = new Semaphore(maxCountOfPassengers, true);
    }

    @Override
    public void run() {
        System.out.println(this + " running");
        try {
            for (Pair<BusStop, Integer> pair : busRoute.getRoute()) {
                BusStop busStop = pair.getFirst();
                Thread.sleep(DRIVING_TIME);
                System.out.println("BEFORE ARRIVE " + this);
                busStop.arrive(this);
                int stayTime = pair.getSecond();
                Thread.sleep(stayTime);
                busStop.leave(this);
                System.out.println("AFTER LEAVE " + this);
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public boolean enter(Passenger passenger) throws InterruptedException {
        semaphore.acquire();
        if (run){
            return false;
        }
        countOfPassengers++;
        return true;
    }

    public void leave(Passenger passenger) throws InterruptedException{
        if ( !run){

        }
    }

    public Bus(BusRoute busRoute, int maxCountOfPassengers) {
        this(busRoute, 0, maxCountOfPassengers);
    }

    public int getMaxCountOfPassengers() {
        return maxCountOfPassengers;
    }

    public void setMaxCountOfPassengers(int maxCountOfPassengers) {
        this.maxCountOfPassengers = maxCountOfPassengers;
    }

    public int getCountOfPassengers() {
        return countOfPassengers;
    }

    public void setCountOfPassengers(int countOfPassengers) {
        this.countOfPassengers = countOfPassengers;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public BusRoute getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }


    @Override
    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", busNumber=" + busNumber +
                ", countOfPassengers=" + countOfPassengers +
                ", maxCountOfPassengers=" + maxCountOfPassengers +
                ", busRoute=" + busRoute +
                '}';
    }

}
