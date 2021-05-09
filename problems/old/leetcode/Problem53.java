package leetcode;

/**
 * Created by Dennis on 11/18/2017.
 *
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.

 24 minutes, but had to see solution
 */
public class Problem53 {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int subsetSoFar = nums[0];
        for(int i = 1; i < nums.length; i++) {
            subsetSoFar = Math.max(subsetSoFar + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, subsetSoFar);
        }
        return maxSoFar;
    }
}
