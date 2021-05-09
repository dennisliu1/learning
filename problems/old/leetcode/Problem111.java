package leetcode;

/**
 * Created by Dennis on 11/28/2017.
 *
 * Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 12 minutes
 */
public class Problem111 {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int minHeight = recurse(root, 1);
        return minHeight;
    }

    public int recurse(TreeNode root, int currHeight) {
        if(root.left == null && root.right == null) return currHeight;
        else {
            int leftMinHeight = (root.left != null) ? recurse(root.left, currHeight+1) : Integer.MAX_VALUE;
            int rightMinHeight = (root.right != null) ?  recurse(root.right, currHeight+1) : Integer.MAX_VALUE;
            return Math.min(leftMinHeight, rightMinHeight);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
