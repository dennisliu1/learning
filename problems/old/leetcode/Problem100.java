package leetcode;

/**
 * Created by Dennis on 11/20/2017.
 * Given two binary trees, write a function to check if they are the same or not.

 Two binary trees are considered the same if they are structurally identical and the nodes have the same value.


 Example 1:

 Input:     1         1
 / \       / \
 2   3     2   3

 [1,2,3],   [1,2,3]

 Output: true
 Example 2:

 Input:     1         1
 /           \
 2             2

 [1,2],     [1,null,2]

 Output: false
 Example 3:

 Input:     1         1
 / \       / \
 2   1     1   2

 [1,2,1],   [1,1,2]

 Output: false

 25 minutes, just edge cases catching
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
public class Problem100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q != null) return false;
        if(p != null && q == null) return false;
        if(p == null && q == null) return true;

        // BFS matching
        LinkedList<TreeNode> queueP = new LinkedList<>();
        LinkedList<TreeNode> queueQ = new LinkedList<>();
        queueP.add(p);
        queueQ.add(q);
        while(!queueP.isEmpty() || !queueQ.isEmpty()) {
            TreeNode nodeP = queueP.removeFirst();
            TreeNode nodeQ = queueQ.removeFirst();
//            System.out.printf("P: %d %d,%d\n", nodeP.val,
//                    (nodeP.left != null) ? nodeP.left.val : -1,
//                    (nodeP.right != null) ? nodeP.right.val : -1 );
//            System.out.printf("Q: %d %d,%d\n", nodeQ.val,
//                    (nodeQ.left != null) ? nodeQ.left.val : -1,
//                    (nodeQ.right != null) ? nodeQ.right.val : -1 );

            if(nodeP.val != nodeQ.val) return false;

            if(nodeP.left == null && nodeQ.left != null) return false;
            if(nodeP.right == null && nodeQ.right != null) return false;
            if(nodeP.left != null && nodeQ.left == null) return false;
            if(nodeP.right != null && nodeQ.right == null) return false;

            if( (nodeP.left != null && nodeQ.left != null) && !(nodeP.left.val == nodeQ.left.val) ) return false;
            if( (nodeP.right != null && nodeQ.right != null) && !(nodeP.right.val == nodeQ.right.val) ) return false;

            if(nodeP.left != null) queueP.add(nodeP.left);
            if(nodeP.right != null) queueP.add(nodeP.right);
            if(nodeQ.left != null) queueQ.add(nodeQ.left);
            if(nodeQ.right != null) queueQ.add(nodeQ.right);
        }

        return true;
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
