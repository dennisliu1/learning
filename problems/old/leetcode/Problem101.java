package leetcode;

/**
 * Created by Dennis on 11/23/2017.
 Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

 1
 / \
 2   2
 / \ / \
 3  4 4  3
 But the following [1,2,2,null,3,null,3] is not:
 1
 / \
 2   2
 \   \
 3    3
 Note:
 Bonus points if you could solve it both recursively and iteratively.
 47 minutes, got distracted
 24 minutes, better after getting some rest
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
public class Problem101 {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;

        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left == null && right == null) return true;
        else if(!(left != null && right != null)) return false; // make sure both are either null or not null

        LinkedList<TreeNode> stackLeft = new LinkedList<>();
        LinkedList<TreeNode> stackRight = new LinkedList<>();
        stackLeft.push(left);
        stackRight.push(right);

        while(!stackLeft.isEmpty() || !stackRight.isEmpty()) {
            printStack(stackLeft);
            printStack(stackRight);
            left = (!stackLeft.isEmpty()) ? stackLeft.pop() : null;
            right = (!stackRight.isEmpty()) ? stackRight.pop() : null;

            if(left == null && right == null) continue;
            else if(!(left != null && right != null)) return false;
            else {
                System.out.printf("compare: %d == %d\n", left.val, right.val);
                if(left.val == right.val) {
                    System.out.printf("children left: %s == %s\n", left.left, right.right);
                    if(left.left == null && right.right == null);
                    else if(left.left != null && right.right != null) {
                        stackLeft.push(left.left);
                        stackRight.push(right.right);
                    }
                    else return false;

                    System.out.printf("children right: %s == %s\n", left.right, right.left);
                    if(left.right == null && right.left == null);
                    else if(left.right != null && right.left != null) {
                        stackRight.push(right.left);
                        stackLeft.push(left.right);
                    }
                    else return false;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    public void printStack(LinkedList<TreeNode> list) {
        for(TreeNode node : list) {
            System.out.print( ((node != null) ? node.val : "null") + ", ");
        }
        System.out.println();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Problem101 p = new Problem101();

//        String[] input = "1,2,2,null,3,null,3".split(",");
//        ArrayList<TreeNode> tree = new ArrayList<>();
//        for(int i = 0; i < input.length; i++) {
//            if(input[i].equals("null")) continue;
//            else {
//                int val = Integer.parseInt(input[i]);
//                tree.add(new TreeNode(val));
//            }
//        }

    }
}
