package algorithm.SegmentTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC315 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] { 5,2,6,1 };
        solution.countSmaller(nums);
    }
}
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int[] minMax = getMinMax(nums);
        SegmentTreeNode root = new SegmentTreeNode(minMax[0], minMax[1]);
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            //query
            ans.addFirst(query(root, nums[i]));
            //update tree;
            update(root, nums[i]);
        }
        return ans;
    }
    private int query(SegmentTreeNode root, int num) {
        if (root == null || root.start >= num) return 0;
        if (num > root.end) return root.count;
        return query(root.left, num) + query(root.right, num);
    }
    private void update(SegmentTreeNode root, int num) {

        if (num < root.start || num > root.end) return;
        root.count++;
        if (root.start == root.end) return;
        int mid = root.start + (root.end - root.start) / 2;
        if (num <= mid) {
            if (root.left == null) {
                root.left = new SegmentTreeNode(root.start, mid);
            }
            update(root.left, num);
        } else {
            if (root.right == null) {
                root.right = new SegmentTreeNode(mid + 1, root.end);
            }
            update(root.right, num);
        }
    }

    private int[] getMinMax(int[] nums) {
        int min = nums[0], max = nums[0];
        for (int n : nums) {
            min = min > n ? n : min;
            max = max < n ? n : max;
        }
        return new int[]{min, max};
    }
    class SegmentTreeNode{
        int start;
        int end;
        int count;
        SegmentTreeNode left;
        SegmentTreeNode right;
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.count = 0;
        }
    }
}
