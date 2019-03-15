package algorithm.leetcodeUncategorized;

public class LC715 {
    public static void main(String[] args) {
        RangeModule rm = new RangeModule();
        rm.addRange(10, 20);
        rm.removeRange(14,16);
        System.out.println(" 1");
    }
}

class RangeModule {
    TreeNode root;
    public RangeModule() {
        root = null;
    }

    public void addRange(int left, int right) {
        root = addRange(root, left, right);
    }

    public boolean queryRange(int left, int right) {
        return queryRange(root, left, right);

    }

    public void removeRange(int left, int right) {
        removeRange(root, left, right);
    }
    private TreeNode removeRange(TreeNode root, int start, int end) {
        if (start >= end) return root;
        if (root == null) return root;
        if (root.end <= start) {
            root.right = removeRange(root.right, start, end);
        } else if (root.start >= end) {
            root.left = removeRange(root.left, start, end);
        } else if (start <= root.start && end >= root.end){
            root.end = root.start;
            root.left = removeRange(root.left, start, root.start);
            root.right = removeRange(root.right, end, root.end);
        } else if (end <= root.end && start >= root.start ) {

            root.left = addRange(root.left, root.start, start);
            root.start = end;// add for here
        } else if (end < root.end && start < root.start) {
            root.start = end;
            root.left = removeRange(root.left, start, root.start);
        } else if (start > root.start && end > root.end) {
            root.end = start;
            root.right = removeRange(root.right, root.end, end);
        }
        return root;
    }

    private boolean queryRange(TreeNode root, int start, int end) {
        if (start >= end) return true;
        if (root == null) return false;
        if (start >= root.end) return queryRange(root.right, start, end);
        if (end <= root.start) return queryRange(root.left, start, end);
        if (start >= root.start && end <= root.end) return true;
        return queryRange(root.left,  start, root.start) && queryRange(root.right, root.end, end);
    }
    private TreeNode addRange(TreeNode root, int start, int end) {
        if (start >= end) return root;
        if (root == null) return new TreeNode(start, end);
        if (root.start >= end) {
            root.left = addRange(root.left, start, end);
        } else if (root.end <= start) {
            root.right = addRange(root.right, start, end);
        } else {
            int a = Math.min(root.start, start);
            int b = Math.max(root.start, start);
            int c = Math.min(root.end, end);
            int d = Math.max(root.end, end);
            root.start = b;
            root.end = c;
            root.left = addRange(root.left, a, b);
            root.right = addRange(root.right, c, d);
        }
        return root;
    }

}
class TreeNode {
    int start, end;
    TreeNode left, right;
    public TreeNode(int start, int end) {
        this.start = start;
        this.end = end;
    }
}