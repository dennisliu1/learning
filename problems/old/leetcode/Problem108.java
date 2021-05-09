package leetcode;

/**
 * Created by Dennis on 11/27/2017.
 *
 *
 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 http://www.geeksforgeeks.org/sorted-array-to-balanced-bst/

 22 minutes, not really good at recursion.
 */
public class Problem108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        TreeNode root = recurse(nums, 0, nums.length-1);
        return root;
    }

    public TreeNode recurse(int[] nums, int start, int end) {
        if(start > end) return null;

        int mid = (int) (start+end)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recurse(nums, start, mid-1);
        root.right = recurse(nums, mid+1, end);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
