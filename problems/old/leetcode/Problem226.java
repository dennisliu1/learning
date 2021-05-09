package leetcode;

import java.util.LinkedList;

/**
 * Created by Dennis on 12/11/2017.
 *
 * Invert a binary tree.

 4
 /   \
 2     7
 / \   / \
 1   3 6   9
 to
 4
 /   \
 7     2
 / \   / \
 9   6 3   1
 Trivia:
 This problem was inspired by this original tweet by Max Howell:
 Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.

 9 minutes
 */
public class Problem226 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;

        LinkedList<TreeNode> queueParent = new LinkedList<>();

        queueParent.add(root);
        int nodes = queueParent.size();
        while(!queueParent.isEmpty()) {
            while(nodes >= 0 && !queueParent.isEmpty()) {
                TreeNode node = queueParent.removeFirst();
                invert(node);
                if(node.left != null) queueParent.add(node.left);
                if(node.right != null) queueParent.add(node.right);
                nodes--;
            }
            nodes = queueParent.size();
        }
        return root;
    }

    public void invert(TreeNode node) {
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

     public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
