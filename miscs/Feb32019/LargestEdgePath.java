package algorithm.miscs.Feb32019;

import algorithm.unionFind.TreeUnionFind;

public class LargestEdgePath {
    int globalMax;
    public int getLargestWeights(TreeNode root) throws Exception {
        globalMax = Integer.MIN_VALUE;
        if (root == null || (root.left == null && root.right == null)) throw new Exception();
        getLargestWeightHelper(root);
        return globalMax;
    }
    public int getLargestWeightHelper(TreeNode root) {
        if (root == null) return 0;
        int left = getLargestWeightHelper(root.left);
        int right = getLargestWeightHelper(root.right);

        int leftSum = 0;
        if (root.left != null) {
            leftSum += left < 0 ?  0 : left;
            leftSum += root.weightLeft;
            globalMax = Math.max(globalMax, leftSum);
        }

        int rightSum = 0;
        if (root.right != null) {
            rightSum += right < 0 ? 0 : right;
            rightSum += root.weightRight;
            globalMax = Math.max(globalMax, rightSum);
        }
        if (leftSum > 0 && rightSum > 0) globalMax = Math.max(globalMax, leftSum + rightSum);
        return Math.max(leftSum, rightSum);
    }
    class TreeNode {
        int id;
        TreeNode left;
        TreeNode right;
        int weightLeft;
        int weightRight;
    }
}

class LargestEdgePath2 {
    int globalMax;
    public int getGlobalMax(TreeNode root) throws Exception {
        if (root == null || (root.left == null && root.right == null)) throw new Exception();
        globalMax = Integer.MIN_VALUE;
        helper(root);
        return globalMax;
    }
    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left), right = helper(root.right);
        if (root.left != null) {
            globalMax = Math.max(globalMax, left);
        }
        if (root.right != null) {
            globalMax = Math.max(globalMax, right);
        }
        if (left > 0 && right > 0) globalMax = Math.max(globalMax, left + right);
        return Math.max(0, Math.max(left, right)) + root.weight;
    }

    class TreeNode {
        int weight;
        TreeNode left;
        TreeNode right;

        public TreeNode(int weight) {
            this.weight = weight;
        }
    }
}


