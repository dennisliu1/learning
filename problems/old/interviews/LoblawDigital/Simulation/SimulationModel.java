package interviews.LoblawDigital.Simulation;

import interviews.LoblawDigital.Building.BuildingModel;
import interviews.LoblawDigital.Building.Floor;
import interviews.LoblawDigital.Elevator.Elevator;
import interviews.LoblawDigital.Elevator.ElevatorSpecs;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Dennis on 11/26/2017.
 *
 * Main Class for controlling the simulation.
 * Mimic, override setup() to create new tests.
 * Simulation model assumes each of the following actions are 1 unit of time:
 * - moving from 1 floor to another = 1 unit
 * - loading, unload = 0 unit
 * - changing elevator state = 0 unit
 */
public class SimulationModel {
    public static final int DEFAULT_NUMBER_FLOORS = 10;
    public static final int DEFAULT_MAX_STEPS = 100;
    int numFloors = 10;
    BuildingModel building;
    Elevator elevator;

    ArrayList<Person> people;
    ArrayList<Cargo> cargoes;

    public SimulationModel() {
        this(DEFAULT_NUMBER_FLOORS);
    }

    public SimulationModel(int numFloors) {
        this.numFloors = numFloors;
        building = new BuildingModel(numFloors);
        elevator = new Elevator(building, ElevatorSpecs.getBasicSpecs());

        building.connectElevatorSignals(elevator.getElevatorSignalHandler());
        people = new ArrayList<>();
        cargoes = new ArrayList<>();
    }

    public void setup() {
        addPerson(3, 6);
        addPerson(4, 6);
        addPerson(5, 3);
        addPerson(6, 0);
    }

    public void reset() {
        people.clear();
        cargoes.clear();
    }

    public void userManualMode(Scanner scanner) {
        int currentStep = 0;
        elevator.printState();

        int currentFloorNumber;
        boolean isRunning = true;
        while(isRunning) {
            boolean askForInput = true;
            while(askForInput) {
                System.out.printf("You are on floor %d. Currently Floor(s) %s are pressed.",
                        elevator.getCurrentFloor(), elevator.getCalledFloorsToString());
                System.out.println("What would you like to do?");
                System.out.print("(go) to a floor; do (no)thing; (exit) to quit the simulation. ");
                String input = scanner.nextLine();

                try {
                    if(input.equals("go")) {
                        System.out.printf("which floor? %d - %d: ", building.lowestFloor(), building.highestFloor());
                        input = scanner.nextLine();
                        int floor = Integer.parseInt(input);
                        if(building.isValidFloor(floor)) {
                            elevator.addFloor(floor);
                            askForInput = false;
                        }
                        else {
                            System.out.println("Sorry that was not a valid floor!");
                        }
                    }
                    else if(input.equals("no")) {
                        System.out.println("<listening to jazz>");
                        askForInput = false;
                    }
                    else if(input.equals("exit")) {
                        askForInput = false;
                        isRunning = false;
                    }
                }
                catch(Exception e) {
                    System.out.println("Sorry, but there was an error with the input. Please try again.");
                }
            }


            currentFloorNumber = elevator.getCurrentFloor();

            unloadCurrentFloor();
            boolean notLoadedEveryone = loadCurrentFloor();

            elevator.readyElevator();
            elevator.printState();

            elevator.moveElevator();
            currentStep++;

            if(notLoadedEveryone) recallElevator(currentFloorNumber);
        }
        elevator.printState();
    }

    public void simulate() {
        simulate(DEFAULT_MAX_STEPS);
    }

    public void simulate( int maxSteps) {
        int currentStep = 0;
        elevator.printState();

        int currentFloorNumber;
        while(elevator.hasDestinations() && currentStep < maxSteps) {
            currentFloorNumber = elevator.getCurrentFloor();

            unloadCurrentFloor();
            boolean notLoadedEveryone = loadCurrentFloor();

            elevator.readyElevator();
            elevator.printState();

            elevator.moveElevator();
            currentStep++;

            if(notLoadedEveryone) recallElevator(currentFloorNumber);
        }
        elevator.printState();
    }

    public void unloadCurrentFloor() {
        elevator.unloadFloor();
    }

    public boolean loadCurrentFloor() {
        int currentFloorNumber = elevator.getCurrentFloor();
        Floor currentFloor = building.getFloor(currentFloorNumber);
        loadPeople(currentFloor);
        loadCargo(currentFloor);
        return (currentFloor.existPersonWaiting() || currentFloor.existCargoWaiting());
    }

    public void loadPeople(Floor currentFloor) {
        Person p = currentFloor.peekFirstPersonInLine();
        boolean loadedPerson = true;
        while(p != null && loadedPerson) {
            loadedPerson = elevator.loadPerson(p);
            if(loadedPerson) {
                currentFloor.getPerson();
            }
            p = currentFloor.peekFirstPersonInLine();
        }
    }

    public void loadCargo(Floor currentFloor) {
        Cargo c = currentFloor.peekFirstCargoInLine();
        boolean loadedCargo = true;
        while(c != null && loadedCargo) {
            loadedCargo = elevator.loadCargo(c);
            if(loadedCargo) {
                currentFloor.getCargo();
            }
            c = currentFloor.peekFirstCargoInLine();
        }
    }

    public void addPerson(int startFloor, int destinationFloor) {
        Person p = Person.generateRandomPerson(destinationFloor);
        people.add(p);
        building.addPersonToFloor(startFloor, p);
    }

    public void addRandomPeople(int numberPeople) {
        for(int i = 0; i < numberPeople; i++) {
            addRandomPerson();
        }
    }

    public void addRandomPerson() {
        int startFloor = building.generateRandomeFloor();
        int destinationFloor = building.generateRandomeFloor();
        while(destinationFloor == startFloor) {
            destinationFloor = building.generateRandomeFloor();
        }
        addPerson(startFloor, destinationFloor);
    }

    public void addCargo(int startFloor, int destinationFloor) {
        Cargo c = Cargo.generateRandomCargo(destinationFloor);
        cargoes.add(c);
        building.addCargoToFloor(startFloor, c);
    }

    public void addRandomCargo(int numberCargo) {
        for(int i = 0; i < numberCargo; i++) {
            addRandomCargo();
        }
    }

    public void addRandomCargo() {
        int startFloor = building.generateRandomeFloor();
        int destinationFloor = building.generateRandomeFloor();
        while(destinationFloor == startFloor) {
            destinationFloor = building.generateRandomeFloor();
        }
        addCargo(startFloor, destinationFloor);
    }

    public void recallElevator(int floorNumber) {
        Floor currentFloor = building.getFloor(floorNumber);
        if(currentFloor.existPersonWaiting()) currentFloor.callElevator(currentFloor.peekFirstPersonInLine());
        else if(currentFloor.existCargoWaiting()) currentFloor.callElevator(currentFloor.peekFirstCargoInLine());
    }
}
