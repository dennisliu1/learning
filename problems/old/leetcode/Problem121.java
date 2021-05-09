package leetcode;

/**
 * Created by Dennis on 11/28/2017.
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.

 If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

 Example 1:
 Input: [7, 1, 5, 3, 6, 4]
 Output: 5

 max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 Example 2:
 Input: [7, 6, 4, 3, 1]
 Output: 0

 In this case, no transaction is done, i.e. max profit = 0.
 */
public class Problem121 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;

        int maxEndingHere = 0,
                maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxEndingHere = Math.max(0, maxEndingHere += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
//    public int maxProfit(int[] prices) {
//        int maxProfit = 0;
//        for(int i = 0; i < prices.length; i++) {
//            maxProfit = maxProfitIfBuyToday(prices, i, maxProfit);
//        }
//        return maxProfit;
//    }
//
//    public int maxProfitIfBuyToday(int[] prices, int buyDay, int currMaxProfit) {
//        int maxProfit = currMaxProfit;
//        int buy = prices[buyDay];
//        for(int i = buyDay+1; i < prices.length; i++) {
//            int sell = prices[i];
//            if(buy > sell) continue;
//            int diff = sell-buy;
//            maxProfit = Math.max(maxProfit, diff);
//        }
//        return maxProfit;
//    }
}
