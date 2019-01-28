package algorithm.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubseqPath {
    public List<Integer> longestIncreasingSubseqPath(int[] array) {
        int[] dp = new int[array.length];
        int[] prev = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }
        int globalMax = -1 ;
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (globalMax < dp[i]) {
                globalMax = dp[i];
                index = i;
            }
        }
        List<Integer> result = new ArrayList<>();
        while (index != -1) {
            result.add(index);
            index = prev[index];
        }
        Collections.reverse(result);
        return result;
    }
}
