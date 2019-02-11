package algorithm.SegmentTree;


import algorithm.myUtility.Display;

import java.util.Arrays;

public class LC307RangeSum {
    public static void main(String[] args) {
        NumArray solution = new NumArray(new int[]{0,9,5,7,3});
        //solution.update(0, 3);
        //Display.myPrint(solution.sumRange(4,4));

        Display.myPrint(solution.sumRange(2,4));

        //["NumArray","update","sumRange","sumRange","update","sumRange"]
        //[[[9,-8]],[0,3],[1,1],[0,1],[1,-3],[0,1]]

        //["NumArray","sumRange","sumRange","sumRange","update","update","update","sumRange","update","sumRange","update"]
        //[[[0,9,5,7,3]],[4,4],[2,4],[3,3],[4,5],[1,7],[0,8],[1,2],[1,9],[4,4],[3,4]]
    }
}

class NumArray {
    int[] nums;
    SegmentTreeNode root;
    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) return;
        this.nums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) this.nums[i] = nums[i];
        this.root = buildTree(0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        return;
    }
    private SegmentTreeNode buildTree(int start, int end) {
        if (start > end) return null;
        if (start == end) return new SegmentTreeNode(start, end, nums[start]);
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = buildTree(start, mid);
        SegmentTreeNode right = buildTree(mid + 1, end);
        SegmentTreeNode root = new SegmentTreeNode(start, end, left.sum + right.sum);
        root.left = left;
        root.right = right;
        return root;
    }

    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        updateHelper(root, i, diff);
    }
    private void updateHelper(SegmentTreeNode node, int index, int diff) {
        if (node == null || node.start > index || node.end < index) return;
        node.sum += diff;
        if (root.start == root.end) return;
        int mid = node.start + (node.end - node.start) / 2;
        if (index <= mid) {
            updateHelper(node.left, index, diff);
        } else {
            updateHelper(node.right, index, diff);
        }
    }

    public int sumRange(int i, int j) {
        return sumRangeHelper(root, i, j);
    }
    private int sumRangeHelper(SegmentTreeNode node, int i, int j) {
        if (node == null || i > j) return 0;
        if (i > node.end || j < node.start) return 0;
        if (i <= node.start && j >= node.end) return node.sum;
        int mid = node.start + (node.end - node.start) / 2;
        if (j <= mid) return sumRangeHelper(node.left, i, j);
        if (i > mid) return sumRangeHelper(node.right, i, j);
        return sumRangeHelper(node.left, i, mid) + sumRangeHelper(node.right, mid + 1, j);
    }
}
class SegmentTreeNode{
    int start;
    int end;
    int sum;
    SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end, int sum){
        this.start = start;
        this.end = end;
        this.sum = sum;
    }
}
