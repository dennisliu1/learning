package leetcode;

/**
 * Created by Dennis on 12/5/2017.
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 13 minutes, kinda tricky - recursive too slow.
 */
public class Problem198 {
    public int rob(int[] nums) {
        // so pick non-consecutive houses
//        return recurse(nums, 0, false,0);

        int prevNo = 0;
        int prevYes = 0;
        for(int n : nums) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes);
            prevYes = n + temp;
        }
        return Math.max(prevNo, prevYes);
    }

    public int recurse(int[] nums, int i, boolean cannotDo, int curr) {
        if(i == nums.length) {
            return curr;
        }
        else {
            int money = nums[i];
            int robHouseMoney = (!cannotDo) ? recurse(nums, i+1, true, curr+money) : 0;
            int notRobHouseMoney = recurse(nums, i+1, false, curr);
            return Math.max(robHouseMoney, notRobHouseMoney);
        }
    }
}
