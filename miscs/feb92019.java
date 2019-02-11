package algorithm.miscs;

import algorithm.myUtility.Display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class feb92019 {
    int[][] OFFSETS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int largestCluster(char[][] array) {
        int ans = 0;
        if (array == null || array.length == 0 || array[0].length == 0) return ans;
        int N = array.length, M = array[0].length;
        boolean[][] visited = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                ans = Math.max(ans, explore(array, r, c, visited));
            }
        }
        return ans;
    }
    private int explore(char[][] array, int r, int c, boolean[][] visited) {
        if (visited[r][c] || array[r][c] != '1') return 0;
        visited[r][c] = true;
        int numberOfNodes = 1;
        for (int[] os : OFFSETS) {
            int nr = os[0] + r, nc = os[1] + c;
            if (nr < 0 || nc < 0 || nr >= array.length || nc >= array[0].length) {
                continue;
            }
            numberOfNodes += explore(array, nr, nc, visited);
        }
        return numberOfNodes;
    }

    public long maxDownSequences(int[] prices) {
        int N = prices.length;
        if (N < 3) return 0;
        long[] dp = new long [N];
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (prices[j] > prices[i]) {
                    dp[i]++;
                }
            }
        }
        long count = 0;
        for (int i = 2; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (prices[i] < prices[j] && dp[j] > 0) {
                    count += dp[j];
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        feb92019 solution = new feb92019();
        int[] prices = new int[]{5, 3, 4, 2, 1};
        Display.myPrint(solution.maxDownSequences(prices));
        Display.myPrint(solution.maxDownSequences(new int[]{4, 1, 3, 2, 5}));

    }

    public static List<String> missingWords(String s, String t) {
        // Write your code here
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        String[] sarray = s.split(" ");
        if (t == null || t.length() == 0) {
            return Arrays.asList(s.split(" "));
        }
        String[] tarray = t.split(" ");
        int pt1 = 0, pt2 = 0;
        while (pt1 < sarray.length && pt2 < tarray.length) {
            if (!sarray[pt1].equals(tarray[pt2])) {
                res.add(sarray[pt1++]);
            } else {
                pt1++;
                pt2++;
            }
        }
        while (pt1 < sarray.length) {
            res.add(sarray[pt1++]);
        }
        return res;
    }



}
