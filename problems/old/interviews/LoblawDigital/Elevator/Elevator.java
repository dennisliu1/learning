package interviews.LoblawDigital.Elevator;

import interviews.LoblawDigital.Building.BuildingModel;
import interviews.LoblawDigital.Simulation.Cargo;
import interviews.LoblawDigital.Simulation.Person;
import interviews.LoblawDigital.interfaces.ElevatorInterface;
import interviews.LoblawDigital.interfaces.ElevatorSignalHandler;

/**
 * Created by Dennis on 11/26/2017.
 */
public class Elevator implements ElevatorInterface {
    private ElevatorPhysicalModel physicalModel;
    private ElevatorModel panel;
    private BuildingModel building;
    private int startingFloor;

    public Elevator(BuildingModel building, ElevatorSpecs specs) {
        this(building, specs, building.lowestFloor());
    }

    public Elevator(BuildingModel building, ElevatorSpecs specs, int startingFloor) {
        this.building = building;
        this.physicalModel = new ElevatorPhysicalModel(specs);
        this.panel = new ElevatorModel(building, startingFloor);
        this.startingFloor = startingFloor;
    }

    public void reset() {
        this.physicalModel.reset();
        this.panel.reset();
    }

    public ElevatorSignalHandler getElevatorSignalHandler() {
        return panel;
    }

    public void readyElevator() {
        panel.readyElevator();
    }

    public void moveElevator() {
        panel.moveElevator();
    }

    public void unloadFloor() {
        physicalModel.unloadPeople(panel.getCurrentFloor());
        physicalModel.unloadCargos(panel.getCurrentFloor());
    }


    public boolean loadPerson(Person person) {
        boolean success = physicalModel.loadPerson(person);
        if(success) {
            panel.addFloor(person.peekDestination());
        }
        return success;
    }

    public boolean loadCargo(Cargo cargo) {
        boolean success = physicalModel.loadCargo(cargo);
        if(success) {
            panel.addFloor(cargo.peekDestination());
        }
        return success;
    }

    public double currentWeight() {
        return physicalModel.getWeight();
    }

    public double currentSize() {
        return physicalModel.getSize();
    }

    public int getCurrentFloor() {
        return panel.getCurrentFloor();
    }

    public void printState() {
        System.out.printf("Floor %d -> %d, %s -> %s: %s w/ %d passengers\n",
                panel.getCurrentFloor(), panel.getNextFloor(),
                panel.getCurrentState(), panel.getNextState(),
                panel.getCalledFloorsToString(), physicalModel.getNumberPassengers());
    }

    public boolean addFloor(int index) {
        if(building.isValidFloor(index)) {
            return panel.addFloor(index);
        }
        else return false;
    }

    public boolean hasDestinations() {
        return panel.hasDestinations();
    }

    public String getCalledFloorsToString() {
        return panel.getCalledFloorsToString();
    }
}