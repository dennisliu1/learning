package interviews.LoblawDigital;

import interviews.LoblawDigital.Simulation.SimulationModel;

import java.util.Scanner;

/**
 * Created by Dennis on 11/25/2017.
 *

 As promised, here is the quiz that I’d like you to work on.
 Again, this is not to judge your coding skills (your answer does not have to be perfect),
 but it is more so as a starting point for a discussion around your coding patterns.

 Elevator Challenge
 In the language of your choice, write a program that takes a user input and simulates the functionality of a single elevator in a building.
 This could be a residential, office, or a construction elevator.
 You can implement it however way you like, and include whatever features or restrictions that you see fit.

 Minimum requirements: The program should take some user input (e.g. via command line or a web interface),
   and should show some output to interact with the user.

So goal:
 Write a class to simulate an elevator.

 Assume no special operating modes
 Assume elevator is a real system
 Assume panel interactions are real systems
 Assume person is a simulation of a real person using an elevator
 Assume program user is someone testing this simulation or API out.


 Building
 - list of floors
 Floor
 - Panel
 - # of people waiting for elevator
 - # of cargo waiting for elevator
 Person
 - destination floor
 - weight
 - size
 Floor External Panel
 - Mode
 Elevator
 - Physical Model - Not actual physical data
    - Spec
        max size, weight
    - Current
        current size, weight
 - Logical Model
    - Floor
    - Direction
    - State

 Simulation
 - Cases
 - Elevator Algorithm
 - User manual control
 */
public class LoblawDigitalProblem {

    public static void main(String[] args) {
        boolean userInputMode = true;
        Scanner scanner = new Scanner(System.in);

        boolean askForInput = true;
        String input;
        int numFloors = 0;

        while(askForInput) {
            try {
                System.out.print("Please enter the number of floors the building has (blank = default of 10): ");
                input = scanner.nextLine();
                if(input.isEmpty()) numFloors = 10;
                else numFloors = Integer.parseInt(input);

                if(numFloors > 0) {
                    askForInput = false;
                }
                else {
                    System.out.println("Sorry, but please enter a valid positive number!");
                }
            }
            catch(Exception e) {
                System.out.println("Sorry, but there was an error with the input. Please try again.");
            }
        }
        askForInput = true;

        while(askForInput) {
            try {
                System.out.print("(0) for testing mode, (1) for user input mode: ");
                input = scanner.nextLine();
                if(input.equals("0")) {
                    userInputMode = false;
                    askForInput = false;
                }
                else if(input.equals("1")) {
                    userInputMode = true;
                    askForInput = false;
                }
                else {
                    System.out.println("please type only 0 or 1");
                }
            }
            catch(Exception e) {
                System.out.println("Sorry, but there was an error with the input. Please try again.");
            }
        }

        SimulationModel sim = new SimulationModel(numFloors);
        if(userInputMode) sim.userManualMode(scanner);
        else {
            sim.setup();
            sim.simulate();
        }
    }
}
// asdf





































































