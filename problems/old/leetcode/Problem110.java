package leetcode;

/**
 * Created by Dennis on 11/27/2017.
 *
 * Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 10 minutes, pretty good
 */
public class Problem110 {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;

        try {
            int maxHeight = getMaxHeight(root, 1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getMaxHeight(TreeNode root, int currHeight) throws Exception {
        if(root == null) return currHeight;
        else {
            int leftHeight = getMaxHeight(root.left, currHeight+1);
            int rightHeight = getMaxHeight(root.right, currHeight+1);
            if(!hasBalance(leftHeight, rightHeight)) throw new Exception("not balanced");
            return Math.max(leftHeight, rightHeight);
        }
    }

    public boolean hasBalance(int leftHeight, int rightHeight) {
        int balance = leftHeight - rightHeight;
        if(-1 <= balance && balance <= 1) return true;
        else return false;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
