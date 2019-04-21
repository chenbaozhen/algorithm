package algorithm.TwoPointers;

import java.lang.reflect.Array;
import java.util.*;

public class TwoPointerExcercise {

    public List<int[]> pairWithLargestSumSmaller(int[] nums, int target) {
        //Given two arbitrary array, find all pairs with largest sum <= target
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        int max = Integer.MIN_VALUE;
        while (l < r) {
            if (nums[l] + nums[r] <= target) {

                if (nums[l] + nums[r] > max) {
                    max = nums[l] + nums[r];
                    ans = new ArrayList<>();
                }
                if (nums[l] + nums[r] == max) {
                    int l1 = l, r1 = r;
                    if (nums[l] == nums[r]) {
                        for (int i = 0; i < (r - l + 1) * (r - l) / 2; i++) {
                            ans.add(new int[]{nums[l], nums[r]});
                        }
                        break;
                    } else {
                        while (l1 < r && nums[l1 + 1] == nums[l] ) {
                            l1++;
                        }
                        while (r1 > l && nums[r1 - 1] == nums[r]) {
                            r1--;
                        }
                        for (int i = 0; i < (l1 - l +1 ) * (r - r1 + 1); i++) {
                            ans.add(new int[]{nums[l], nums[r]});
                        }
                    }
                    l = l1 + 1;
                    r = r1 - 1;

                } else {
                    l++;
                }
            } else {
                r--;
            }
        }
        return ans;

    }
    public List<int[]> pairWithLargestSumSmaller(int[] a, int[] b, int target) {
        TreeMap<Integer, List<Integer>> tMap = new TreeMap<>();
        for (int i = 0; i < a.length; i++) {
            tMap.putIfAbsent(a[i], new ArrayList<>());
            tMap.get(a[i]).add(i);
        }
        List<int[]> ans = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < b.length; i++) {
            Integer best = tMap.floorKey(target - b[i]);
            if (best == null) continue;
            if (best + b[i] > max) {
                max = best + b[i];
                ans = new ArrayList<>();
            }
            if (best + b[i] == max) {
                List<Integer> lst = tMap.get(best);
                for (Integer id : lst) {
                    ans.add(new int[]{id, i});
                }
            }
        }
        return ans;
    }
    public boolean canConstructTriangle(int[] nums) {
        Arrays.sort(nums);
        for (int i = 2; i < nums.length; i++) {

            if (nums[i - 2] + nums[i - 1] > nums[i]) return true;
        }
        return false;
    }
    public int numberOfPairDiff(int[] nums, int target) {
        Arrays.sort(nums);
        int slow = 0, fast = 0;
        int ans = 0;
        while (fast < nums.length) {
            if (nums[fast] - nums[slow] == target) {
                ans++;
                fast++;
                slow++;
            } else if (nums[fast] - nums[slow] < target) {
                fast++;
            } else if (nums[fast] - nums[slow] > target ) {
                slow++;
            }
        }
        return ans;
    }
    public int numberOfPairDiffII(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int n : nums) {
            ans += map.getOrDefault(n - target, 0);
            ans += map.getOrDefault(n + target, 0);
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        return ans;

    }

}
