package interviews.LoblawDigital.Elevator;

/**
 * Created by Dennis on 11/26/2017.
 */
public class ElevatorSpecs {
    public static final double BASIC_MAX_WEIGHT = 1000;
    public static final double BASIC_MAX_SIZE = 20;

    private final double maxWeight;
    private final double maxSize;

    public ElevatorSpecs(double maxWeight, double maxSize) {
        this.maxWeight = maxWeight;
        this.maxSize = maxSize;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public double getMaxSize() {
        return maxSize;
    }

    public static ElevatorSpecs getBasicSpecs() {
        return new ElevatorSpecs(BASIC_MAX_WEIGHT, BASIC_MAX_SIZE);
    }
}