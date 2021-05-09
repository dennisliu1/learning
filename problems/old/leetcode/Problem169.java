package leetcode;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by Dennis on 12/1/2017.
 *
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

 You may assume that the array is non-empty and the majority element always exist in the array.

 9 minutes
 */
public class Problem169 {
    public int majorityElement(int[] nums) {
        TreeMap<Integer, Integer> bucket = new TreeMap<>();

        for(int i = 0; i < nums.length; i++) {
            int count = bucket.getOrDefault(nums[i], 0);
            bucket.put(nums[i], count+1);
        }

        int max = Integer.MIN_VALUE;
        int maxNum = Integer.MIN_VALUE;
        for(Integer num : bucket.keySet()) {
            int count = bucket.get(num);
            if(count > max) {
                max = count;
                maxNum = num;
            }
        }

        return maxNum;
    }
}
