package leetcode;

/**
 * Created by Dennis on 11/27/2017.
 *
 * Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 15 minutes - easy
 */

import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Problem104 {

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        LinkedList<StackNode> stack = new LinkedList<>();
        stack.push(new StackNode(root, 1));

        int maxHeight = -1;
        while(!stack.isEmpty()) {
            StackNode element = stack.pop();
            TreeNode node = element.node;
            int currHeight = element.height;

            if(node.left == null && node.right == null) {
                maxHeight = Math.max(maxHeight, currHeight);
            }
            else {
                if(node.left != null) {
                    stack.add(new StackNode(node.left, currHeight+1));
                }
                if(node.right != null) {
                    stack.add(new StackNode(node.right, currHeight+1));
                }
            }
        }
        return maxHeight;
    }

    class StackNode {
        TreeNode node;
        int height;
        StackNode(TreeNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}
