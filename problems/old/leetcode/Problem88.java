package leetcode;

/**
 * Created by Dennis on 11/20/2017.
 Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:
 You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class Problem88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = m+n-1;
        int num1, num2;
        while(k >= 0) {
            num1 = (i >= 0) ? nums1[i] : Integer.MIN_VALUE;
            num2 = (j >= 0) ? nums2[j] : Integer.MIN_VALUE;
            if(num1 >= num2) {
                nums1[k] = num1;
                i--;
            }
            else {
                nums1[k] = num2;
                j--;
            }
            // System.out.printf("%2d = %d\n", k, nums1[k]);
            k--;
        }
    }
}
