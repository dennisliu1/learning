package interviews.kooltra;

import java.util.*;

/**
 * Created by Dennis on 12/1/2017.
 */
public class GiftBasketPackager {
    public enum Season {
        Spring, Summer, Autumn, Winter
    };

    public enum GiftType {
        perfect, weight, shape, perfect_pairing, shape_pairing, discount
    }

    private ArrayList<Gift> gifts;
    private int k;
    private Season season;
    private Map<Season, GiftType[]> seasonTypes;
    private List<Gift[]> subsets;
    private Map<GiftType, List<Gift[]>> buckets;
    private Gift[] optimal;

    public GiftBasketPackager(ArrayList<Gift> gifts, int k, Season season) {
        this.gifts = gifts;
        this.k = k;
        this.season = season;
        seasonTypes = getSeasonTypes();
        optimal = null;
        init();
    }

    public void init() {
        subsets = getSubsets(gifts, k);
        buckets = sortSubsetsIntoTypes(subsets, k, season, seasonTypes);
        optimal = getOptimal(buckets);
    }


    public List<Gift[]> getSubset() {
        System.out.println();
        printSubsets(subsets);
        return subsets;
    }


    public Map<GiftType, List<Gift[]>> getBuckets() {
        System.out.println();
        printBuckets(buckets);
        return buckets;
    }

    public Gift[] getOptimal() {
        System.out.println();
        System.out.println("optimal");
        printBasket(optimal);
        return optimal;
    }

    private List<Gift[]> getSubsets(List<Gift> input, int k) {
        ArrayList<Gift[]> subsets = new ArrayList<>();
        int[] subsetIndexes = new int[k]; // subset indexes iteration

        if(k <= input.size()) {
            // first index sequence: 0, 1, 2,...
            int iter = 0;
            for(int i = 0; (iter) < k-1; i++) {
                iter = subsetIndexes[i] = i;
            }

            subsets.add(getSubset(input, subsetIndexes)); // adds the basic case
            printIndexes(subsetIndexes);

            boolean isRunning = true;
            while(isRunning) {
                int i;
                // find pos of item that can be incremented
                for(i = k -1; i >= 0 && subsetIndexes[i] == input.size()- k+i; i--) continue;
                System.out.printf("i=%d ", i);
                if(i < 0) {
                    System.out.println();
                    isRunning = false; // end condition
                    break;
                }


                subsetIndexes[i]++; // increment that slot

                i++;
                // when iterating beyond last column,
                while(i < k) {
                    // fill in remaining items
                    subsetIndexes[i] = subsetIndexes[i - 1] + 1;
                    i++;
                }

                printIndexes(subsetIndexes);

                subsets.add(getSubset(input, subsetIndexes));
            }
        }
        return subsets;
    }

    private Gift[] getSubset(List<Gift> input, int[] subset) {
        Gift[] result = new Gift[subset.length];

        for(int i = 0; i < subset.length; i++) {
            result[i] = input.get(subset[i]);
        }
        return result;
    }

    private Map<GiftType, List<Gift[]>> sortSubsetsIntoTypes(List<Gift[]> subsets, int k, Season season, Map<Season, GiftType[]> seasonTypes) {
        Map<GiftType, List<Gift[]>> buckets = new HashMap<>();
        GiftType[] thisSeasonTypes = seasonTypes.get(season); // types the season will support
        for(GiftType giftType : thisSeasonTypes) {// init buckets
            buckets.put(giftType, new ArrayList<Gift[]>());
        }

        for(int i = 0; i < subsets.size(); i++) {
            Gift[] basket = subsets.get(i);

            if(Arrays.asList(thisSeasonTypes).contains(GiftType.perfect) && isPerfectVariety(basket)) {// if this season has perfect type
                List<Gift[]> bucket = buckets.get(GiftType.perfect);
                bucket.add(basket);
            }
            else if(Arrays.asList(thisSeasonTypes).contains(GiftType.weight) && isWeightVariety(basket)) {// if this season has perfect type
                List<Gift[]> bucket = buckets.get(GiftType.weight);
                bucket.add(basket);
            }
            else if(Arrays.asList(thisSeasonTypes).contains(GiftType.shape) && isShapeVariety(basket)) {// if this season has perfect type
                List<Gift[]> bucket = buckets.get(GiftType.shape);
                bucket.add(basket);
            }
            else if(Arrays.asList(thisSeasonTypes).contains(GiftType.perfect_pairing) && isPerfectPairing(basket)) {// if this season has perfect type
                List<Gift[]> bucket = buckets.get(GiftType.perfect_pairing);
                bucket.add(basket);
            }
            else if(Arrays.asList(thisSeasonTypes).contains(GiftType.shape_pairing) && isShapePairing(basket)) {// if this season has perfect type
                List<Gift[]> bucket = buckets.get(GiftType.shape_pairing);
                bucket.add(basket);
            }
            else if(Arrays.asList(thisSeasonTypes).contains(GiftType.discount)) {// if this season has perfect type
                List<Gift[]> bucket = buckets.get(GiftType.discount);
                bucket.add(basket);
            }
        }

        // sort lists so weight wise biggest is first
        for(GiftType type : buckets.keySet()) {
            List<Gift[]> bucket = buckets.get(type);
            Collections.sort(bucket, (o1, o2) -> {
                Gift[] basket1 = (Gift[]) o1;
                Gift[] basket2 = (Gift[]) o2;
                int sum1 = 0;
                int sum2 = 0;
                for(Gift g : basket1) sum1 += g.getWeight();
                for(Gift g : basket2) sum2 += g.getWeight();
                if(sum1 < sum2) return 1;
                else if(sum1 > sum2) return -1;
                else return 0;
            });
        }
        return buckets;
    }

    private boolean isPerfectVariety(Gift[] basket) {
        HashSet<Gift.Shape> diffShapes = new HashSet<>();
        HashSet<Integer> diffWeights = new HashSet<>();
        for(Gift gift : basket) {
            diffShapes.add(gift.getShape());
            diffWeights.add(gift.getWeight());
        }
        if(diffShapes.size() == basket.length && diffWeights.size() == basket.length) return true;
        else return false;
    }

    private boolean isWeightVariety(Gift[] basket) {
        HashSet<Gift.Shape> diffShapes = new HashSet<>();
        HashSet<Integer> diffWeights = new HashSet<>();
        for(Gift gift : basket) {
            diffShapes.add(gift.getShape());
            diffWeights.add(gift.getWeight());
        }
        if(diffShapes.size() == 1 && diffWeights.size() == basket.length) return true;
        else return false;
    }

    private boolean isShapeVariety(Gift[] basket) {
        HashSet<Gift.Shape> diffShapes = new HashSet<>();
        HashSet<Integer> diffWeights = new HashSet<>();
        for(Gift gift : basket) {
            diffShapes.add(gift.getShape());
            diffWeights.add(gift.getWeight());
        }
        if(diffShapes.size() == basket.length && diffWeights.size() == 1) return true;
        else return false;
    }

    private boolean isPerfectPairing(Gift[] basket) {
        HashSet<Gift.Shape> diffShapes = new HashSet<>();
        HashSet<Integer> diffWeights = new HashSet<>();
        for(Gift gift : basket) {
            diffShapes.add(gift.getShape());
            diffWeights.add(gift.getWeight());
        }
        if(diffShapes.size() == 2 && diffWeights.size() == 2) return true;
        else return false;
    }

    private boolean isShapePairing(Gift[] basket) {
        HashSet<Gift.Shape> diffShapes = new HashSet<>();
        HashSet<Integer> diffWeights = new HashSet<>();
        for(Gift gift : basket) {
            diffShapes.add(gift.getShape());
            diffWeights.add(gift.getWeight());
        }
        if(diffShapes.size() == 2 && diffWeights.size() == basket.length) return true;
        else return false;
    }


    private Gift[] getOptimal(Map<GiftType, List<Gift[]>> types) {
        Set<GiftType> giftTypes = types.keySet();
        if(giftTypes.contains(GiftType.perfect)) {
            List<Gift[]> baskets = types.get(GiftType.perfect);
            if(!baskets.isEmpty()) return baskets.get(0); //sorted, so get first
        }
        if(giftTypes.contains(GiftType.weight)) {
            List<Gift[]> baskets = types.get(GiftType.weight);
            if(!baskets.isEmpty()) return baskets.get(0); //sorted, so get first
        }
        if(giftTypes.contains(GiftType.shape)) {
            List<Gift[]> baskets = types.get(GiftType.shape);
            if(!baskets.isEmpty()) return baskets.get(0); //sorted, so get first
        }
        if(giftTypes.contains(GiftType.perfect_pairing)) {
            List<Gift[]> baskets = types.get(GiftType.perfect_pairing);
            if(!baskets.isEmpty()) return baskets.get(0); //sorted, so get first
        }
        if(giftTypes.contains(GiftType.shape_pairing)) {
            List<Gift[]> baskets = types.get(GiftType.shape_pairing);
            if(!baskets.isEmpty()) return baskets.get(0); //sorted, so get first
        }
        if(giftTypes.contains(GiftType.discount)) {
            List<Gift[]> baskets = types.get(GiftType.discount);
            if(!baskets.isEmpty()) return baskets.get(0); //sorted, so get first
        }
        return null;
    }

    private static Map<Season, GiftType[]> getSeasonTypes() {
        Map<Season, GiftType[]> seasonTypes = new HashMap<>();
        seasonTypes.put(Season.Spring, new GiftType[] {
                GiftType.perfect_pairing, GiftType.shape_pairing, GiftType.discount
        });
        seasonTypes.put(Season.Summer, new GiftType[] {
                GiftType.perfect, GiftType.weight, GiftType.shape, GiftType.perfect_pairing, GiftType.shape_pairing, GiftType.discount
        });
        seasonTypes.put(Season.Autumn, new GiftType[] {
                GiftType.perfect, GiftType.weight, GiftType.shape, GiftType.discount
        });
        seasonTypes.put(Season.Winter, new GiftType[] {
                GiftType.perfect, GiftType.perfect_pairing, GiftType.discount
        });
        return seasonTypes;
    }

    public static void printIndexes(int[] s) {
        for(int num : s) {
            System.out.printf("%d, ", num);
        }
        System.out.println();
    }

    public static void printSubsets(List<Gift[]> subsets) {
        System.out.println("subsets: ");
        for(Gift[] subset : subsets) {
            for(Gift gift : subset) {
                System.out.printf("%dg %s, ", gift.getWeight(), gift.getShape());
            }
            System.out.println();
        }
    }

    public static void printBuckets(Map<GiftType, List<Gift[]>> buckets) {
        System.out.println("buckets: ");
        for(GiftType type : buckets.keySet()) {
            System.out.printf("bucket %s: \n", type);
            List<Gift[]> bucket = buckets.get(type);
            for(Gift[] basket : bucket) {
                System.out.print("\t");
                printBasket(basket);
            }
        }
    }

    public static void printBasket(Gift[] basket) {
        for(Gift gift : basket) {
            System.out.printf("%dg %s, ", gift.getWeight(), gift.getShape());
        }
        System.out.println();
    }
}
