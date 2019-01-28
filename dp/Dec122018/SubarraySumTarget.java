package algorithm.dp.Dec122018;

import myUtility.Display;

import java.util.HashSet;
import java.util.Set;

public class SubarraySumTarget {
    /*
    Determine if there exists an subarray with sum == target
     */

    public boolean subArraySumTarget(int[] A, int target) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        set.add(sum);
        for (int n : A) {
            sum += n;
            if (set.contains(sum - target)) return true;
            set.add(sum);
        }
        return false;
    }
    public static void main(String[] args) {
        SubarraySumTarget solution = new SubarraySumTarget();
        int[] A = new int[] { 1, -3, 2};
        Display.myPrint(solution.subArraySumTarget(A, -1));
    }
}
