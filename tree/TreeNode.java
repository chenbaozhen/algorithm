package algorithm.tree;
import java.util.*;
public class TreeNode {

    int val;
    TreeNode left, right;
    /*
    Constructor
     */
    public TreeNode(int val) {
        this.val = val;
    }

    /*
    Build tree from level order traversal
     */
    public static TreeNode buildTree(String[] levelOrder) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (levelOrder.length == 0 || levelOrder[0].indexOf("#") >= 0) return null;
        TreeNode root = new TreeNode(Integer.parseInt(levelOrder[0]));
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            node.left = getTreeNode(levelOrder, index++);
            node.right = getTreeNode(levelOrder, index++);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
    private static TreeNode getTreeNode(String[] levelOrder, int index) {
        if (index >= levelOrder.length) return null;
        if (levelOrder[index].indexOf("#") >= 0) return null;
        return new TreeNode(Integer.parseInt(levelOrder[index].trim()));
    }
}

class InOrderIterator {
    Deque<TreeNode> stack;
    public InOrderIterator(TreeNode root) {
        stack = new LinkedList<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    public TreeNode next(){
        if (!hasNext()) return null;
        TreeNode ans = stack.pop();
        if (ans.right != null) {
            TreeNode node = ans.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return ans;
    }
}
class UnitTest{
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
        InOrderIterator iterator = new InOrderIterator(root);
        while (iterator.hasNext())  System.out.println(iterator.next().val);
    }
}
