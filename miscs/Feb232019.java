package algorithm.miscs;

public class Feb232019 {
    public int[] findKClosest(int[] nums, int target ,int k) {
        int larger = findEqualOrlarger(nums, target);
        int lesser = larger - 1;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int diffLg = larger < nums.length ? nums[larger] - target : Integer.MAX_VALUE;
            int diffLs = lesser >= 0 ? target - nums[lesser] : Integer.MAX_VALUE;
            if (diffLg <= diffLs) {
                ans[i] = nums[larger++];
            } else {
                ans[i] = nums[lesser--];
            }
        }
        return nums;
    }
    public int findEqualOrlarger(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;
            if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (nums[l] >= target) return l;
        return l + 1;
    }
}
