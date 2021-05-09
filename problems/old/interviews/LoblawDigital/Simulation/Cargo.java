package interviews.LoblawDigital.Simulation;

import java.util.LinkedList;

/**
 * Created by Dennis on 11/26/2017.
 */
public class Cargo {
    public static final double STANDARD_WEIGHT = 400;
    public static final double CARGO_SIZE = 2;

    private double weight;
    private double size;
    private LinkedList<Integer> destinations; // generally only one, used for simulations of specific people

    public Cargo(double weight, double size) {
        this.weight = weight;
        this.size = size;
        destinations = new LinkedList<>();
    }

    public double getWeight() {
        return weight;
    }

    public double getSize() {
        return size;
    }

    public void addDestination(int floor) {
        destinations.add(floor);
    }

    public int peekDestination() {
        return destinations.peekFirst();
    }

    public int removeDestination() {
        return destinations.removeFirst();
    }

    public static Cargo generateRandomCargo(int destinationFloor) {
        return generateRandomCargo(STANDARD_WEIGHT, destinationFloor);
    }


    public static Cargo generateRandomCargo(double weight, int destinationFloor) {
        Cargo c = new Cargo(weight, CARGO_SIZE);
        c.addDestination(destinationFloor);
        return c;
    }

    public static Cargo generateRandomCargo(double minWeight, double maxWeight, int minFloor, int maxFloor) {
        double weight = generateRandomWeight(minWeight, maxWeight);
        int destinationFloor = generateRandomFloor(minFloor, maxFloor);
        return generateRandomCargo(weight, destinationFloor);
}

    public static double generateRandomWeight(double minWeight, double maxWeight) {
        return (Math.random() * (maxWeight-minWeight) + 1) + minWeight;
    }

    public static int generateRandomFloor(int minFloor, int maxFloor) {
        return (int)(Math.random() * (maxFloor-minFloor) + 1) + minFloor;
    }
}
