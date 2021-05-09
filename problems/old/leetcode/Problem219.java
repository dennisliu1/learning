package leetcode;

import java.util.HashMap;

/**
 * Created by Dennis on 12/6/2017.
 *
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array such that
 * nums[i] = nums[j] and the absolute difference between i and j is at most k.
 *
 * 13 minutes, not focused - was pretty easy tho
 */
public class Problem219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> prevPosMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            if(prevPosMap.containsKey(nums[i])) {
                int prevPos = prevPosMap.get(nums[i]);
                if(i-prevPos <= k) return true;
            }
            prevPosMap.put(nums[i], i);
        }

        return false;
    }
}
