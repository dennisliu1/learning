package interviews.kooltra;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Dennis on 12/1/2017.
 */
public class mainTest {
    @Test
    public void basicTest() throws Exception {
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

    @Test
    public void perfectTest() throws Exception {
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

    @Test
    public void weightTest() throws Exception {
        ArrayList<Gift> sample = new ArrayList<>();
        sample.add(new Gift(Gift.Shape.Square, 1));
        sample.add(new Gift(Gift.Shape.Square, 2));
        sample.add(new Gift(Gift.Shape.Square, 3));
        sample.add(new Gift(Gift.Shape.Square, 4));
        sample.add(new Gift(Gift.Shape.Square, 5));
        sample.add(new Gift(Gift.Shape.Square, 6));
        sample.add(new Gift(Gift.Shape.Square, 7));
        int k = 5;
        GiftBasketPackager.Season season = GiftBasketPackager.Season.Spring;

        GiftBasketPackager packager = new GiftBasketPackager(sample, k, season);
        packager.getSubset();
        packager.getBuckets();
        packager.getOptimal();
    }

    @Test
    public void shapeTest() throws Exception {
        ArrayList<Gift> sample = new ArrayList<>();
        sample.add(new Gift(Gift.Shape.Square, 1));
        sample.add(new Gift(Gift.Shape.Rectangle, 1));
        sample.add(new Gift(Gift.Shape.Circle, 1));
        sample.add(new Gift(Gift.Shape.Oval, 1));
        sample.add(new Gift(Gift.Shape.Triangle, 1));
        sample.add(new Gift(Gift.Shape.Square, 6));
        sample.add(new Gift(Gift.Shape.Rectangle, 7));
        int k = 5;
        GiftBasketPackager.Season season = GiftBasketPackager.Season.Spring;

        GiftBasketPackager packager = new GiftBasketPackager(sample, k, season);
        packager.getSubset();
        packager.getBuckets();
        packager.getOptimal();
    }

    @Test
    public void perfectPairTest() throws Exception {
        ArrayList<Gift> sample = new ArrayList<>();
        sample.add(new Gift(Gift.Shape.Square, 1));
        sample.add(new Gift(Gift.Shape.Square, 2));
        sample.add(new Gift(Gift.Shape.Circle, 3));
        sample.add(new Gift(Gift.Shape.Circle, 4));
        sample.add(new Gift(Gift.Shape.Circle, 5));
        sample.add(new Gift(Gift.Shape.Square, 6));
        sample.add(new Gift(Gift.Shape.Rectangle, 7));
        int k = 5;
        GiftBasketPackager.Season season = GiftBasketPackager.Season.Spring;

        GiftBasketPackager packager = new GiftBasketPackager(sample, k, season);
        packager.getSubset();
        packager.getBuckets();
        packager.getOptimal();
    }

    @Test
    public void pairTest() throws Exception {
        ArrayList<Gift> sample = new ArrayList<>();
        sample.add(new Gift(Gift.Shape.Square, 1));
        sample.add(new Gift(Gift.Shape.Square, 1));
        sample.add(new Gift(Gift.Shape.Circle, 2));
        sample.add(new Gift(Gift.Shape.Circle, 2));
        sample.add(new Gift(Gift.Shape.Circle, 2));
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