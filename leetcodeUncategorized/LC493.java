package algorithm.leetcodeUncategorized;

public class LC493 {


    int[] nums;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        int[] ans = new int[1];
        int[] helper = new int[nums.length];

        mergeSort(0, nums.length - 1, ans, helper);
        return ans[0];
    }
    private void mergeSort( int start, int end, int[] ans, int[] helper) {
        if (start >= end) return;
        int m = start + (end - start) / 2;
        mergeSort( start, m, ans, helper);
        mergeSort(m + 1, end, ans, helper);
        merge(start, m, end, ans, helper);
    }
    private void merge(int left, int mid, int right, int[] ans, int[] helper) {
        for (int i = left; i <= right; i++) {
            helper[i] = nums[i];
        }
        // 2 4   1 3 5
        int l = left, r = mid + 1;
        int pt = left, pt2 = left;
        while (l <= mid || r  <= right) {
            if (r > right || (l <= mid && nums[l] < nums[r])) {
                nums[pt++] = helper[l++];
            } else {
                nums[pt++] = helper[r];
                while (pt2 <= mid && nums[pt2] <= nums[r] * 2L) {
                    pt2++;
                }
                ans[0] += (mid - pt2 + 1);
                r++;
            }
        }

    }
    public static void main(String[] args) {
        LC493 solution = new LC493();
        solution.reversePairs(new int[]{2, 4, 3, 5, 1});
    }

}
