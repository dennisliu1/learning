package interviews.LoblawDigital.Building;

import interviews.LoblawDigital.interfaces.ElevatorSignalHandler;

/**
 * Created by Dennis on 11/26/2017.
 */
public class FloorModel {
    public static final int CALL_ELEVATOR_TIMEOUT = 30; // number of actions elevator takes before timing out this call.
    private int isCallingElevatorUp;
    private int isCallingElevatorDown;
    private ElevatorSignalHandler elevatorSignalHandler;
    private int floorNumber;

    public FloorModel(int floorNumber) {
        this(floorNumber, null);
    }

    public FloorModel(int floorNumber, ElevatorSignalHandler elevatorSignalHandler) {
        this.floorNumber = floorNumber;
        connectElevatorToPanel(elevatorSignalHandler);
        isCallingElevatorUp = 0;
        isCallingElevatorDown = 0;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void connectElevatorToPanel(ElevatorSignalHandler elevatorSignalHandler) {
        this.elevatorSignalHandler = elevatorSignalHandler;
    }

    public void callElevator() {
        signalElevator();
    }

    // timeout modeling for elevator calls. Ended up not doing it because of the added complexity.
    // leaving it here for discussion purposes.

    public void callElevatorUp() {
        isCallingElevatorUp = CALL_ELEVATOR_TIMEOUT;
        signalElevator();
    }

    public void callElevatorDown() {
        isCallingElevatorDown = CALL_ELEVATOR_TIMEOUT;
        signalElevator();
    }

    public boolean isCallingUp() {
        return isCallingElevatorUp > 0;
    }

    public boolean isCallingDown() {
        return isCallingElevatorDown > 0;
    }


    public boolean countdownElevatorCallUp() {
        if(isCallingElevatorUp > 0) {
            isCallingElevatorUp--;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean countdownElevatorCallDown() {
        if(isCallingElevatorDown > 0) {
            isCallingElevatorDown--;
            return true;
        }
        else {
            return false;
        }
    }

    private void signalElevator() {
        if(elevatorSignalHandler != null) elevatorSignalHandler.callElevator(floorNumber);
    }
}
