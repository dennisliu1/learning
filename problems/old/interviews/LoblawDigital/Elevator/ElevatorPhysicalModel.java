package interviews.LoblawDigital.Elevator;

import interviews.LoblawDigital.Simulation.Cargo;
import interviews.LoblawDigital.Simulation.Person;

import java.util.ArrayList;

/**
 * Created by Dennis on 11/26/2017.
 *
 *
 */
public class ElevatorPhysicalModel {
    private ElevatorSpecs specs;
    private ElevatorLoads loads;

    public ElevatorPhysicalModel(ElevatorSpecs specs) {
        this.specs = specs;
        loads = new ElevatorLoads();
    }

    public boolean loadPerson(Person person) {
        if(validAddition(person)) {
            loads.loadPerson(person);
            return true;
        }
        else return false;
    }

    public boolean loadCargo(Cargo cargo) {
        if(validAddition(cargo)) {
            loads.loadCargo(cargo);
            return true;
        }
        else return false;
    }

    public ArrayList<Person> unloadPeople(int floorIndex) {
        return loads.unloadPeople(floorIndex);
    }

    public ArrayList<Cargo> unloadCargos(int floorIndex) {
        return loads.unloadCargos(floorIndex);
    }

    public boolean validAddition(Cargo cargo) {
        return (loads.getWeight() + cargo.getWeight() < specs.getMaxWeight()) &&
                (loads.getSize() + cargo.getSize() < specs.getMaxSize());
    }

    public double getWeight() {
        return loads.getWeight();
    }

    public double getSize() {
        return loads.getSize();
    }

    public void reset() {
        loads.reset();
    }

    public int getNumberPeople() {
        return loads.getNumberPeople();
    }

    public int getNumberCargoes() {
        return loads.getNumberCargoes();
    }

    public int getNumberPassengers() {
        return loads.getNumberPassengers();
    }
}
