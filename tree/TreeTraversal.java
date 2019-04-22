package algorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeTraversal {

}

class TreeTraversalInorder {
    public void traversalInorder(TreeNode root) {
        if (root == null) return;
        traversalInorder(root.left);
        System.out.println(root.val);
        traversalInorder(root.right);
    }

    public void traversalInorderIterative(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        firstNode(deque, root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            System.out.println(node.val);
            if (node.right != null) {
                firstNode(deque, node.right);
            }
        }
    }
    private void firstNode(Deque < TreeNode > stack, TreeNode root){
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}

class UnitTest2{
    public static void main(String[] args) {
                         /*
                  1
                 / \
                2   3
               / \
              4  5
                  \
                   6
                  */
        String treeSer = "1, 2, 3, 4, 5, #, #, #, #, #, 6";
        String[] list = treeSer.split(",");
        TreeNode root = TreeNode.buildTree(list);
        TreeTraversalInorder tt = new TreeTraversalInorder();
        tt.traversalInorderIterative(root);
        tt.traversalInorder(root);
    }
}
