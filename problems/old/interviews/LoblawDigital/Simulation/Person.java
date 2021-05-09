package interviews.LoblawDigital.Simulation;

/**
 * Created by Dennis on 11/26/2017.
 *
 * Simulated model of a person.
 * only care about their space and weight usage, and their
 */
public class Person extends Cargo {
    public static final double STANDARD_WEIGHT = 200;
    public static final double PERSON_SIZE = 1;

    public Person(double weight) {
        super(weight, PERSON_SIZE);
    }

    public static Person generateRandomPerson(int destinationFloor) {
        return generateRandomPerson(STANDARD_WEIGHT, destinationFloor);
    }

    public static Person generateRandomPerson(double weight, int destinationFloor) {
        Person p = new Person(weight);
        p.addDestination(destinationFloor);
        return p;
    }

    public static Person generateRandomPerson(double minWeight, double maxWeight, int minFloor, int maxFloor) {
        double weight = generateRandomWeight(minWeight, maxWeight);
        int destinationFloor = generateRandomFloor(minFloor, maxFloor);
        return generateRandomPerson(weight, destinationFloor);
    }
}