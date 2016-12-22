package lab.bus.stop;

import lab.bus.entity.Bus;
import lab.bus.entity.Passenger;
import lab.bus.start.StaticObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 * @author Ihar Zharykau
 */
public class BusStop implements StaticObject {
    private static int ID = 0;
    private String id = "busStop" + ID++;
    private ConcurrentLinkedQueue<Passenger> passengers;
    private ConcurrentLinkedQueue<Bus> buses;
    private int maxCountOfBuses;

    private Semaphore semaphore;

    public BusStop(int maxCountOfBuses) {
        passengers = new ConcurrentLinkedQueue<>();
        buses = new ConcurrentLinkedQueue<>();
        this.maxCountOfBuses = maxCountOfBuses;
        semaphore = new Semaphore(maxCountOfBuses, true);
    }

    public void arrive(Bus bus) throws InterruptedException{
        semaphore.acquire();
        buses.add(bus);
        System.out.println(bus + " arrived " + this);
    }

    public void leave(Bus bus) throws InterruptedException{
        buses.remove(bus);
        semaphore.release();
        System.out.println(bus + " leave " + this);
    }

    public ConcurrentLinkedQueue<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ConcurrentLinkedQueue<Passenger> passengers) {
        this.passengers = passengers;
    }

    public ConcurrentLinkedQueue<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ConcurrentLinkedQueue<Bus> buses) {
        this.buses = buses;
    }

    public int getMaxCountOfBuses() {
        return maxCountOfBuses;
    }

    public void setMaxCountOfBuses(int maxCountOfBuses) {
        this.maxCountOfBuses = maxCountOfBuses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
