//package interviews;
//
//// Hi, my name is Pawel and I'll be your interviewer today.
//// Please take a look at the link below for more information about the problem we'll be working on together
//// https://wealthsimple.quip.com/UiybABH8nDBa/Activity-Aggregator-Candidate
//
//// Hey Dennis, can I call you now // Or would you like a few minutes?
//// well I'm reading the problem right now, would it make a difference?
//// Lets connect now, that way I can also answer any questions you have. We're working on this together :)
//// sounds good :D
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.HashMap;
//import java.util.Collections;
//
///**
// * Created by Dennis on 11/23/2017.
// */
//public class wealthsimple {
//
//    class Activity {
//        public String date;
//        public String currency;
//        public String transactionType;
//        public double netAmount;
//        public String symbol;
//        public double quantity;
//        public double price;
//
//        public Activity(String date, String currency, String transactionType, double netAmount, String symbol, double quantity, double price) {
//            this.date = date;
//            this.currency = currency;
//            this.transactionType = transactionType;
//            this.netAmount = netAmount;
//            this.symbol = symbol;
//            this.quantity = quantity;
//            this.price = price;
//        }
//
//        public String toString() {
//            return String.format("%s,%s,%s,%s,%s,%s,%s", date, currency, transactionType, netAmount, symbol, quantity, price);
//        }
//    }
//
//    class Activities {
//        public static List<Activity> VALUES = new ArrayList<Activity>();
//
//        static {
//            // date, currency, transactiontype, netAmount, symbol, quantity, price
//            // date, transactiontype
//            // map (dates to transaction types)
//
//            // 1. sort by date
//            // 2. sort by transaction type
//
//            // only 3 transaction types
//            VALUES.add(new Activity("2016-11-30", "CAD", "EFT", 2200.69, null, 0.0, 0.0));
//            VALUES.add(new Activity("2015-02-12", "CAD", "EFT", 567.69, null, 0.0, 0.0));
//            VALUES.add(new Activity("2015-02-12", "CAD", "EFT", 1000.69, null, 0.0, 0.0));
//            VALUES.add(new Activity("2015-02-18", "CAD", "ADJ", -133.4, null, 0.0, 0.0));
//            VALUES.add(new Activity("2015-02-18", "USD", "ADJ", 107.0, null, 0.0, 0.0));
//            VALUES.add(new Activity("2015-02-12", "CAD", "BUY", -22.09, "PHR", 1.0, 22.09));
//            VALUES.add(new Activity("2015-02-12", "CAD", "BUY", -22.09, "AAPL", 1.0, 22.09));
//        }
//    }
//
//    class Solution {
//
//        HashMap<String, ArrayList<Activity>> aggregate;
//        ArrayList<String> dates;
//
//        // 1. aggregate by date
//        public HashMap<String, ArrayList<Activity>> aggregateDataDate(List<Activity> values) {
//            HashMap<String, ArrayList<Activity>> aggregate = new HashMap<>();
//            for(Activity activity : values) {
//                String date = activity.date;
//                ArrayList<Activity> list = aggregate.getOrDefault(date, new ArrayList<Activity>());
//                list.add(activity);
//                aggregate.put(date, list);
//            }
//            return aggregate;
//        }
//
//        public ArrayList<String> getUniqueDates(HashMap<String, ArrayList<Activity>> aggregate) {
//            ArrayList<String> dates = new ArrayList<String>();
//            //System.out.println(aggregate.keySet());
//            for(String date : aggregate.keySet()) {
//                dates.add(date);
//            }
//            Collections.sort(dates);
//            return dates;
//        }
//
//        public void outputData(HashMap<String, ArrayList<Activity>> aggregate, ArrayList<String> dates) {
//            for(int i = dates.size()-1; i >= 0; i--) {
//                String date = dates.get(i);
//                ArrayList<Activity> activities = aggregate.get(date);
//                ArrayList<Activity> activitiesEFT = new ArrayList<Activity>();
//                ArrayList<Activity> activitiesBUY = new ArrayList<Activity>();
//                ArrayList<Activity> activitiesADJ = new ArrayList<Activity>();
//                for(Activity activity : activities) {
//                    if(activity.transactionType.equals("EFT")) {
//                        activitiesEFT.add(activity);
//                    }
//                    else if(activity.transactionType.equals("ADJ")) {
//                        activitiesADJ.add(activity);
//                    }
//                    else if(activity.transactionType.equals("BUY")) {
//                        activitiesBUY.add(activity);
//                    }
//                }
//
//                // BUY
//                if(!activitiesBUY.isEmpty()) {
//                    System.out.printf("%s: We bought %d asset(s) for you:\n", date, activitiesBUY.size());
//                    for(Activity activity : activitiesBUY) {
//                        System.out.printf("\tBought %.2f share(s) of %s at $%.2f/share\n", activity.quantity, activity.symbol, activity.price);
//                    }
//                }
//
//                // ADJ
//                if(!activitiesADJ.isEmpty()) {
//                    Activity actNeg = activitiesADJ.get(0);
//                    Activity actPos = activitiesADJ.get(1);
//                    if(actNeg.netAmount >= 0) {
//                        actNeg = activitiesADJ.get(1);
//                        actPos = activitiesADJ.get(0);
//                    }
//                    System.out.printf("%s: We converted $%.2f %s to $%.2f %s\n",
//                            date, actNeg.netAmount * -1, actNeg.currency, actPos.netAmount, actPos.currency);
//                }
//
//
//
//                // EFT
//                if(!activitiesEFT.isEmpty()) {
//                    System.out.printf("%s: We received your %d deposit(s):\n", date, activitiesEFT.size());
//                    for(Activity activity : activitiesEFT) {
//                        System.out.printf("\t$%.2f %s\n", activity.netAmount, activity.currency);
//                    }
//                }
//            }
//        }
//
//
//        public static void main(String[] args) {
//            Solution sol = new Solution();
//            sol.aggregate = sol.aggregateDataDate(Activities.VALUES);
//            sol.dates = sol.getUniqueDates(sol.aggregate);
//
//            // System.out.println(sol.dates.size());
//            // for(int i = 0; i < sol.dates.size(); i++) {
//            //   System.out.println(sol.dates.get(i));
//            // }
//
//            sol.outputData(sol.aggregate, sol.dates);
//
//            // for (Activity activity : Activities.VALUES) {
//            //   System.out.println(activity);
//            // }
//        }
//
//    }
//}
