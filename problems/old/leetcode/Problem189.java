package leetcode;

/**
 * Created by Dennis on 12/4/2017.
 *
 * Rotate an array of n elements to the right by k steps.

 For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

 Note:
 Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

 [show hint]

 Related problem: Reverse Words in a String II

 14 minutes, didn't know solution
 */
public class Problem189 {
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length <= 1) return;

        int shift = k % nums.length;
        reverse(nums, 0, nums.length-shift-1);
        reverse(nums, nums.length-shift, nums.length-1);
        reverse(nums, 0, nums.length-1);
    }
    public void reverse(int[] nums, int start, int end) {
        int temp = 0;
        int i = start, j = end;
        while(i < j) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
