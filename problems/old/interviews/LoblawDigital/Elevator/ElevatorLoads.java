package interviews.LoblawDigital.Elevator;

import interviews.LoblawDigital.Simulation.Cargo;
import interviews.LoblawDigital.Simulation.Person;

import java.util.ArrayList;

/**
 * Created by Dennis on 11/26/2017.
 */
public class ElevatorLoads {
    private ArrayList<Person> people;
    private ArrayList<Cargo> cargoes;
    private double weight;
    private double size;

    public ElevatorLoads() {
        people = new ArrayList<>();
        cargoes = new ArrayList<>();
        weight = 0;
        size = 0;
    }

    public double getWeight() {
        return weight;
    }

    public double getSize() {
        return size;
    }

    public void loadPerson(Person person) {
        people.add(person);
        weight += person.getWeight();
        size += person.getSize();
    }

    public void loadCargo(Cargo cargo) {
        cargoes.add(cargo);
        weight += cargo.getWeight();
        size += cargo.getSize();
    }

    public ArrayList<Person> unloadPeople(int floorIndex) {
        if(people.isEmpty()) return null;

        ArrayList<Person> result = new ArrayList<>();
        for(Person p : people) {
            if(p.peekDestination() != floorIndex) continue;
            weight -= p.getWeight();
            size -= p.getSize();
            result.add(p);
        }
        for(Person p : result) {
            people.remove(p);
        }
        return result;
    }

    public ArrayList<Cargo> unloadCargos(int floorIndex) {
        if(cargoes.isEmpty()) return null;

        ArrayList<Cargo> result = new ArrayList<>();
        for(Cargo c : cargoes) {
            if(c.peekDestination() != floorIndex) continue;
            weight -= c.getWeight();
            size -= c.getSize();
            result.add(c);
        }
        for(Cargo c : result) {
            cargoes.remove(c);
        }
        return result;
    }

    public void reset() {
        people.clear();
        cargoes.clear();
        weight = 0;
        size = 0;
    }

    public int getNumberPeople() {
        return people.size();
    }

    public int getNumberCargoes() {
        return cargoes.size();
    }

    public int getNumberPassengers() {
        return getNumberPeople() + getNumberCargoes();
    }
}
