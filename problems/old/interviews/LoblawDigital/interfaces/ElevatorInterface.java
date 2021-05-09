package interviews.LoblawDigital.interfaces;

import interviews.LoblawDigital.Simulation.Cargo;

/**
 * Created by Dennis on 11/27/2017.
 */
public interface ElevatorInterface {
    public void unloadFloor();
    public boolean loadCargo(Cargo cargo);

    public int getCurrentFloor();

    public void readyElevator();
    public void moveElevator();
}
