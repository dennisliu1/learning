package leetcode;

/**
 * Created by Dennis on 11/27/2017.
 *
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its bottom-up level order traversal as:
 [
 [15,7],
 [9,20],
 [3]
 ]

 10 minutes - easy
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Problem107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int nodesAtDepth = queue.size();
        int i = 0;
        ArrayList<Integer> levelResult = null;

        while(!queue.isEmpty()) {
            if(i == 0) {
                levelResult = new ArrayList<>();
            }

            TreeNode node = queue.removeFirst();
            levelResult.add(node.val);
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
            i++;

            if(i >= nodesAtDepth) {
                nodesAtDepth = queue.size();
                result.add(0, levelResult);
                i = 0;
            }
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
