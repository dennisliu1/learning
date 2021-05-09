package interviews.LoblawDigital.Building;

import interviews.LoblawDigital.Simulation.Cargo;
import interviews.LoblawDigital.Simulation.Person;
import interviews.LoblawDigital.interfaces.ElevatorSignalHandler;

import java.util.ArrayList;

/**
 * Created by Dennis on 11/26/2017.
 */
public class BuildingModel {
    private ArrayList<Floor> floors;

    public BuildingModel(int num) {
        floors = new ArrayList<>();
        for(int i = 0;  i < num; i++) {
            Floor floor = new Floor(this, i);
            floors.add(floor);
        }
    }

    public void connectElevatorSignals(ElevatorSignalHandler elevatorSignalHandler) {
        for(int i = 0;  i < floors.size(); i++) {
            floors.get(i).connectElevatorSignal(elevatorSignalHandler);
        }
    }

    public int lowestFloor() {
        return 0;
    }

    public int highestFloor() {
        return floors.size()-1;
    }

    public boolean isValidFloor(int index) {
        return lowestFloor() <= index && index <= highestFloor();
    }

    public Floor getFloor(int index) {
        if(isValidFloor(index)) return floors.get(index);
        else return null;
    }

    public void addPersonToFloor(int floorNumber, Person person) {
        getFloor(floorNumber).addPerson(person);
    }

    public void addCargoToFloor(int floorNumber, Cargo cargo) {
        getFloor(floorNumber).addCargo(cargo);
    }

    public int generateRandomeFloor() {
        return generateRandomeFloor(lowestFloor(), highestFloor());
    }

    public int generateRandomeFloor(int minFloor, int maxFloor) {
        return (int)(Math.random() * (maxFloor-minFloor) + 1) + minFloor;
    }
}