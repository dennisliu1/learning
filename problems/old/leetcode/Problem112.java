package leetcode;

/**
 * Created by Dennis on 11/28/2017.
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

 For example:
 Given the below binary tree and sum = 22,
 5
 / \
 4   8
 /   / \
 11  13  4
 /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 12 minutes
 */
public class Problem112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        boolean result = false;
        result = recurse(root, 0, sum);
        return result;
    }

    public boolean recurse(TreeNode root, int currSum, int sum) {
        if(root.left == null && root.right == null) return (currSum+root.val == sum);
        else {
            boolean result = false;
            result |= (root.left != null) ? recurse(root.left, currSum+root.val, sum) : false;
            result |= (root.right != null) ? recurse(root.right, currSum+root.val, sum) : false;
            return result;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
