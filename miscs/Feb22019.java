package algorithm.miscs;

public class Feb22019 {
    public boolean contains(TreeNode s, TreeNode t) {
        if (t == null) return true;
        if (s == null) return false;
        if (equals(s, t)) return true;
        return contains(s.left, t) || contains(s.right, t);
    }
    private boolean equals(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return equals(s.left, t.left) && equals(s.right, t.right);
    }


    public int findSum(TreeNode root, int lo, int hi) {
        if (root == null) return 0;
        int sum = 0;
        if (root.val <= hi && root.val >= lo) sum += root.val;
        if (root.val > lo) {
            sum += findSum(root.left, lo, hi);
        }
        if (root.val < hi) {
            sum += findSum(root.right, lo, hi);
        }
        return sum;
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
