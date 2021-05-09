package interviews.LoblawDigital.Elevator;

import interviews.LoblawDigital.Building.BuildingModel;
import interviews.LoblawDigital.interfaces.ElevatorSignalHandler;

import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Dennis on 11/26/2017.
 * Elevator Algorithm:
 * Continue traveling in the same direction while there are remaining requests in that same direction.
 If there are no further requests in that direction, then stop and become idle, or change direction if there are requests in the opposite direction.
 */
public class ElevatorModel implements ElevatorSignalHandler {
    public enum ELEVATOR_STATE {
        IDLE, GOING_UP, GOING_DOWN
    };

    private BuildingModel building;
    private int currFloor;

    private LinkedList<Integer> calledFloorsOrder;
    private TreeSet<Integer> calledFloors; // sorted
    private SortedSet<Integer> calledFloorsUp; // these are reflection lists, they auto update.
    private SortedSet<Integer> calledFloorsDown;

    private ELEVATOR_STATE currState;

    private int nextFloor;
    private ELEVATOR_STATE nextState;
    private int startingFloor;

    public ElevatorModel(BuildingModel building) {
        this(building, building.lowestFloor());
    }

    public ElevatorModel(BuildingModel building, int startingFloor) {
        this.building = building;
        currFloor = startingFloor;
        calledFloorsOrder = new LinkedList<>();
        calledFloors = new TreeSet<>();
        calledFloorsUp = getCalledFloorsUp();
        calledFloorsDown = getCalledFloorsDown();

        currState = ELEVATOR_STATE.IDLE;
        this.startingFloor = startingFloor;
    }

    // floor addition, get, removal
    public boolean addFloor(int index) {
        if(!calledFloors.contains(index)) {
            calledFloorsOrder.add(index);
            calledFloors.add(index);
            return true;
        }
        else return false;
    }

    public void removeFloor(int index) {
        calledFloorsOrder.remove((Integer) index); // cast so it removes by object, not by index.
        calledFloors.remove((Integer) index);
    }

    public int getNextFloorUp() {
        if(!calledFloorsUp.isEmpty()) return calledFloorsUp.first();
        else return -1;
    }

    public int getNextFloorDown() {
        if(!calledFloorsDown.isEmpty()) return calledFloorsDown.last();
        else return -1;
    }

    public int getNextFloorPriority() {
        return calledFloorsOrder.remove(0);
    }

    // move elevator
    public boolean gotoFloor(int index) {
        if(building.isValidFloor(index)) {
            currFloor = index;
            if(calledFloorsOrder.contains(index)) {
                removeFloor(currFloor);
            }
            if(currFloor == nextFloor) {
                currState = ELEVATOR_STATE.IDLE;
                nextState = ELEVATOR_STATE.IDLE;
            }
            return true;
        }
        else return false;
    }

    public boolean goTowardsFloor() {
        if(currState == ELEVATOR_STATE.GOING_UP) {
            return gotoFloor(currFloor + 1);
        }
        else if(currState == ELEVATOR_STATE.GOING_DOWN) {
            return gotoFloor(currFloor - 1);
        }
        else return false;
    }

    // currState modeling
    public void readyElevator() {
        nextFloor = getNextFloor(currState);
        nextState = getNextState(nextFloor);
    }

    public void moveElevator() {
        // reset options
        if(currState == ELEVATOR_STATE.IDLE)  {
            currState = nextState;
        }
        if(currFloor == nextFloor) {
            currState = ELEVATOR_STATE.IDLE;
        }

        if(goTowardsFloor()) {
            currState = nextState;

            nextFloor = -1;
            nextState = ELEVATOR_STATE.IDLE;
        }
    }

    /*
    given current currState and called floors, get the next floor.
    Tries to get the next floor in a certain direction, if the elevator was moving.
    Otherwise it gets the next elevator in the queue.
    if no called floors, returns the current floor.

    note that the sortedSet reflected tailSet and headSet are sorted already.
    So when going up, getting the max of [curr..n] = nth floor = highest floor
    and likewise min of [0..curr] = first floor = lowest floor
     */
    public int getNextFloor(ELEVATOR_STATE state) {
        int nextFloor;
        if(state == ELEVATOR_STATE.GOING_UP && !calledFloorsUp.isEmpty()) nextFloor = calledFloorsUp.first();
        else if(state == ELEVATOR_STATE.GOING_DOWN && !calledFloorsDown.isEmpty()) nextFloor = calledFloorsDown.last();
        else if(!calledFloorsOrder.isEmpty()) nextFloor = calledFloorsOrder.get(0);
        else nextFloor = currFloor;
        return nextFloor;
    }

    /*
    Given the next floor the elevator will move to, set the currState accordingly.
     */
    public ELEVATOR_STATE getNextState(int nextFloor) {
        if(nextFloor > currFloor) return ELEVATOR_STATE.GOING_UP;
        else if(nextFloor < currFloor) return ELEVATOR_STATE.GOING_DOWN;
        else return ELEVATOR_STATE.IDLE;
    }

    private SortedSet<Integer> getCalledFloorsUp() {
        return calledFloors.tailSet(currFloor, true);
    }

    private SortedSet<Integer> getCalledFloorsDown() {
        return calledFloors.headSet(currFloor, true);
    }

    @Override
    public void callElevator(int floorNumber) {
        addFloor(floorNumber);
    }

    public int getCurrentFloor() {
        return currFloor;
    }

    public ELEVATOR_STATE getCurrentState() {
        return currState;
    }

    public int getNextFloor() {
        return nextFloor;
    }

    public ELEVATOR_STATE getNextState() {
        return nextState;
    }

    public String getCalledFloorsToString() {
        return calledFloorsOrder.toString();
    }

    public void reset() {
        calledFloorsOrder.clear();
        calledFloors.clear();
        currState = ELEVATOR_STATE.IDLE;
        currFloor = startingFloor;
        nextFloor = -1;
        nextState = ELEVATOR_STATE.IDLE;
    }

    public boolean hasDestinations() {
        return !calledFloorsOrder.isEmpty();
    }
}
