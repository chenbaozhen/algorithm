package algorithm.tree.treeNodeParent;

import java.util.*;

public class TreeNodeParent {

    int val;
    TreeNodeParent left, right, parent;

    public TreeNodeParent (int val) {
        this.val = val;
    }
    public static TreeNodeParent buildTree(String[] levelOrder) {
        Queue<TreeNodeParent> queue = new LinkedList<>();
        if (levelOrder.length == 0 || "#".equals(levelOrder[0])) return null;
        TreeNodeParent root = new TreeNodeParent(Integer.parseInt(levelOrder[0]));
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNodeParent node = queue.poll();
            node.left = getTreeNode(levelOrder, index++);
            node.right = getTreeNode(levelOrder, index++);
            if (node.left != null) node.left.parent = node;
            if (node.right != null) node.right.parent = node;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
    public static TreeNodeParent getTreeNode(String[] levelOrder, int i) {
        if (i >= levelOrder.length || "#".equals(levelOrder[i]) ||" #".equals(levelOrder[i])) return null;
        return new TreeNodeParent(Integer.parseInt(levelOrder[i].trim()));
    }
}

class TreeIterator {
    TreeNodeParent node;
    public TreeIterator(TreeNodeParent root) {
        node = root;
        while (node.left != null) {
            node = node.left;
        }
    }
    public boolean hasNext() {
        return node != null;
    }
    public int next() {
        if (node == null) return -1;

        int ans = node.val;
        if (node.right != null) {
            node = node.right;
            while (node.left != null) node = node.left;
        } else {
            while (node.parent != null && node == node.parent.right) node = node.parent;
            node = node.parent;
        }
        return ans;
    }

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
        TreeNodeParent root = TreeNodeParent.buildTree(list);
        TreeIterator iterator = new TreeIterator(root);
        while (iterator.hasNext())  System.out.println(iterator.next());
    }
}