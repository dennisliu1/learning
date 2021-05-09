package leetcode;

/**
 * Created by Dennis on 12/1/2017.
 *
 * Given an integer n, return the number of trailing zeroes in n!.

 Note: Your solution should be in logarithmic time complexity.

 15 + 3 = had to look up solution, suck at math clearly.
 */
public class Problem172 {
    public int trailingZeroes(int n) {
        int numZeroes = 0;
        int pow = 1;
        double numFives;
        while( (numFives = n/Math.pow(5, pow)) > 0) {
            numZeroes += numFives;
            pow++;
        }
        return numZeroes;
    }
}
