package algorithm.dp.Dec122018;

import algorithm.myUtility.Display;

import java.util.TreeMap;

public class LargestSubArrayLessThanTarget {
    /* Find the subarray with the largest sum that is <= target */
    public int[] largestSubArrayLessThanTarget(int[] A, int target) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, -1);
        int sum = 0;
        int globalBest = Integer.MIN_VALUE;
        int[] indices = new int[2];
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            int floor = sum - target;
            Integer best = map.floorKey(floor);
            if (best == null) continue;
            if (best > globalBest) {
                globalBest = best;
                indices = new int[]{map.floorEntry(floor).getValue() + 1, i};
            }
            map.put(sum, i);
        }
        return indices;
    }

    public static void main(String[] args) {
        LargestSubArrayLessThanTarget solution = new LargestSubArrayLessThanTarget();
        int[] A = new int[]{1, -3, 2};
        Display.myPrint(solution.largestSubArrayLessThanTarget(A, -1));
    }
}
