package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 12/5/2017.
 *
 * Write an algorithm to determine if a number is "happy".

 A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

 Example: 19 is a happy number

 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1

 8 minutes
 */
public class Problem202 {
    public boolean isHappy(int n) {
        if(n == 1) return true;

        List<Integer> attempts = new ArrayList<>();
        int total = n;
        attempts.add(total);
        while(true) {
            List<Integer> digits = toDigitArray(total);
            total = 0;
            for(int digit : digits) {
                total += digit * digit;
            }
            System.out.printf("total: %d\n", total);
            if(attempts.contains(total)) return false;
            attempts.add(total);
            if(total == 1) return true;
        }
    }

    public List<Integer> toDigitArray(int n) {
        List<Integer> result = new ArrayList<>();
        int x = n;
        while(x > 0) {
            int digit = x % 10;
            result.add(digit);
            x /= 10;
        }
        return result;
    }
}
