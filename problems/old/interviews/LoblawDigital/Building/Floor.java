package interviews.LoblawDigital.Building;

import interviews.LoblawDigital.Simulation.Cargo;
import interviews.LoblawDigital.Simulation.Person;
import interviews.LoblawDigital.interfaces.ElevatorSignalHandler;

import java.util.PriorityQueue;

/**
 * Created by Dennis on 11/26/2017.
 */
public class Floor {
    private PriorityQueue<Person> personLine;
    private PriorityQueue<Cargo> cargoLine;
    private FloorModel panel;

    private BuildingModel building;
    private int currentFloorNumber;

    public Floor(BuildingModel building, int currentFloorNumber) {
        this(building, currentFloorNumber, new FloorModel(currentFloorNumber));
    }

    public Floor(BuildingModel building, int currentFloorNumber, FloorModel panel) {
        this.building = building;
        this.currentFloorNumber = currentFloorNumber;
        personLine = new PriorityQueue<>();
        cargoLine = new PriorityQueue<>();
        this.panel = panel;
    }

// Cargo related functions
    // add
    public void addPerson(Person person) {
        personLine.add(person);
        callElevator(person);
    }

    public void addCargo(Cargo cargo) {
        cargoLine.add(cargo);
        callElevator(cargo);
    }

    // remove - insert into elevator
    public Person getPerson() {
        if(existPersonWaiting()) return personLine.remove();
        else return null;
    }

    public Cargo getCargo() {
        if(existCargoWaiting()) return cargoLine.remove();
        else return null;
    }

    // remove - leave queue
    public boolean dequeuePerson(Person person) {
        if(existPersonWaiting()) return personLine.remove(person);
        else return false;
    }

    public boolean dequeueCargo(Cargo cargo) {
        if(existCargoWaiting()) return cargoLine.remove(cargo);
        else return false;
    }

// elevator functions
    // check if floor called for elevator
//    public boolean isElevatorCalledUp() {
//        return panel.isCallingUp();
//    }
//
//    public boolean isElevatorCalledDown() {
//        return panel.isCallingDown();
//    }

    // check if there is any cargo waiting
    public boolean existPersonWaiting() {
        return !personLine.isEmpty();
    }

    public boolean existCargoWaiting() {
        return !cargoLine.isEmpty();
    }

// panel specific
    // call elevator
    public boolean callElevatorUp(Cargo cargo) {
        if(panel.isCallingUp()) return false;

        int destinationFloor = cargo.peekDestination();
        if(destinationFloor > currentFloorNumber) {
            panel.callElevatorUp();
            return true;
        }
        else return false;
    }

    public boolean callElevatorDown(Cargo cargo) {
        if(panel.isCallingDown()) return false;

        int destinationFloor = cargo.peekDestination();
        if(destinationFloor < currentFloorNumber) {
            panel.callElevatorDown();
            return true;
        }
        else return false;
    }

    public boolean callElevator(Cargo cargo) {
        return callElevatorUp(cargo) || callElevatorDown(cargo);
    }

    public void connectElevatorSignal(ElevatorSignalHandler elevatorSignalHandler) {
        panel.connectElevatorToPanel(elevatorSignalHandler);
    }

    public Person peekFirstPersonInLine() {
        if(!personLine.isEmpty()) return personLine.peek();
        else return null;
    }

    public Cargo peekFirstCargoInLine() {
        if(!cargoLine.isEmpty()) return cargoLine.peek();
        else return null;
    }

    // if either countdown returns false, it means it resetted.
    // not doing anything with it, but worth discussing about.
    public boolean countdownCalls() {
        boolean countdownDone = !panel.countdownElevatorCallUp();
        countdownDone |= !panel.countdownElevatorCallDown();
        return countdownDone;
    }
}
