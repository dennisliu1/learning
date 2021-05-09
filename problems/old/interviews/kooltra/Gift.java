package interviews.kooltra;

/**
 * Created by Dennis on 12/1/2017.
 */
public class Gift implements Comparable {

    public enum Shape {
            Square, Rectangle, Circle, Oval, Triangle
    };

    private Shape shape;
    private int weight;

    public Gift(Shape shape, int weight) {
        this.shape = shape;
        this.weight = weight;
    }

    public Shape getShape() {
        return shape;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Object o) {
        Gift g = (Gift) o;
        int otherWeight = g.getWeight();
        if(weight < otherWeight) return 1;
        else if(weight > otherWeight) return -1;
        else return 0;
    }
}
