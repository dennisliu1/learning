/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
    	int[] result = new int[2];
    	for(int i = 0; i <= nums.length-2; i++) {
        	int num1 = nums[i];
            for(int j = i+1; j <= nums.length-1; j++) {
                int num2 = nums[j];
                if(num1 + num2 == target) {
                	result[0] = i;
                	result[1] = j;
                }
            }
        }
        return result;
    }
}