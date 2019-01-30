package algorithm.dp.Dec122018;

import algorithm.myUtility.Display;

import java.util.HashMap;
import java.util.Map;

public class LongestShortestSubArray {
    /*
    Find the longest/shortest subarray with sum == target
     */
    public static int[] longestSubArray(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int[] res = new int[]{-1, -1};
        int longest = -1;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];

            Integer last = map.get(sum - target);
            if (last != null) {
                if (i - last > longest) {
                    longest = i - last;
                    res = new int[]{last + 1, i};
                }
            }
            if (map.get(sum) == null) {
                map.put(sum, i);
            }
        }
        return res;
    }
    public static int[] shortestSubArray(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int[] res = new int[]{-1, -1};
        int shortest = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];

            Integer last = map.get(sum - target);

            if (last != null) {
                if (i - last < shortest) {
                    shortest = i - last;
                    res = new int[]{last + 1, i};
                }
            }

            map.put(sum, i);

        }
        return res;
    }
    public static void main(String[] args) {
        int[] A = new int[]{0, 1, 0, 1};
        Display.myPrint(LongestShortestSubArray.longestSubArray(A, 1));
        Display.myPrint(LongestShortestSubArray.shortestSubArray(A, 1));
    }
}
