package algorithm.miscs.Feb32019;

import algorithm.unionFind.TreeUnionFind;

public class LargestEdgePath {

    public int getLargestWeights(TreeNode root) throws Exception {
        int[] globalMax = new int[]{Integer.MIN_VALUE};
        if (root == null || (root.left == null && root.right == null)) throw new Exception();
        getLargestWeightHelper(root, globalMax);
        return globalMax[0];
    }
    public int getLargestWeightHelper(TreeNode root, int[] globalMax) {
        if (root == null) return 0;
        int left = getLargestWeightHelper(root.left, globalMax);
        int right = getLargestWeightHelper(root.right, globalMax);

        int leftSum = 0;
        if (root.left != null) {
            leftSum += (left < 0 ?  0 : left) + root.weightLeft ;
            globalMax[0] = Math.max(globalMax[0], leftSum);
        }

        int rightSum = 0;
        if (root.right != null) {
            rightSum += (right < 0 ? 0 : right) + root.weightRight;
            globalMax[0] = Math.max(globalMax[0], rightSum);
        }
        if (leftSum > 0 && rightSum > 0) globalMax[0] = Math.max(globalMax[0], leftSum + rightSum);
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

    public int getGlobalMax(TreeNode root) throws Exception {
        if (root == null || (root.left == null && root.right == null)) throw new Exception();
        int[] globalMax = new int[]{Integer.MIN_VALUE};

        helper(root, globalMax);

        return globalMax[0];
    }
    private int helper(TreeNode root, int[] globalMax) {
        if (root == null) return 0;

        int left = helper(root.left, globalMax);
        int right = helper(root.right, globalMax);

        if (root.left != null) globalMax[0] = Math.max(globalMax[0], left);
        if (root.right != null) globalMax[0] = Math.max(globalMax[0], right);

        if (left > 0 && right > 0) globalMax[0] = Math.max(globalMax[0], left + right);

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


