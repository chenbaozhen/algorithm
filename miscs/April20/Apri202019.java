package algorithm.miscs.April20;

import java.util.List;

public class Apri202019 {


}
class TreeIterator {
    TreeNode node;
    TreeIterator(TreeNode root) {
        node = root;
        while (node.left != null) {
            node = node.left;
        }
    }
    public int next() {
        /*
         1
        / \
       2   3
      / \
     4  5
         \
          6
         */
        if (node == null) {
            return -1;
        }
        int ans = node.val;
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
        } else {
            if (node.parent != null && node == node.parent.right) {
                while (node.parent != null && node == node.parent.right) {
                    node = node.parent;
                }
            }
            node = node.parent;
        }
        return ans;
    }
}

class TreeNode{
    int val;
    TreeNode left, right, parent;

    public TreeNode(int val) {
        this.val = val;
    }
}
