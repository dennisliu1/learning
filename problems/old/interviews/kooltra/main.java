package interviews.kooltra;

import java.util.*;

/**
 * Created by Dennis on 12/1/2017.
 */
public class main {
    public static void main(String[] args) {
        ArrayList<Gift> sample = new ArrayList<>();
        sample.add(new Gift(Gift.Shape.Square, 1));
        sample.add(new Gift(Gift.Shape.Rectangle, 2));
        sample.add(new Gift(Gift.Shape.Circle, 3));
        sample.add(new Gift(Gift.Shape.Oval, 4));
        sample.add(new Gift(Gift.Shape.Triangle, 5));
        sample.add(new Gift(Gift.Shape.Square, 6));
        sample.add(new Gift(Gift.Shape.Rectangle, 7));
        int k = 5;
        GiftBasketPackager.Season season = GiftBasketPackager.Season.Spring;

        GiftBasketPackager packager = new GiftBasketPackager(sample, k, season);
        packager.getSubset();
        packager.getBuckets();
        packager.getOptimal();
    }
}
