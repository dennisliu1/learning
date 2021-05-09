package leetcode;

/**
 * Created by Dennis on 12/1/2017.
 *
 * Given an array of integers, every element appears twice except for one. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 3 minutes, but had to look up solution - forgot about xor
 */
public class Problem136 {
    public int singleNumber(int[] nums) {
        // O(n), no extra memory
        // A^A = 0 (XOR need the complement, so equal = 0)
        int missing = 0;
        for(int num : nums) {
            missing ^= num;
        }
        return missing;
    }
}
