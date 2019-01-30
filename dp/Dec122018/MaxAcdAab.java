package algorithm.dp.Dec122018;

import algorithm.myUtility.Display;

public class MaxAcdAab {
    /*
    Given an n * n matrix A[i][j] of integers, find maximum value A[c][d] - A[a][b] over all choices of indices such that
    both c > a and d > b
     */

    public int maxAcdAab(int[][] A) {
        int res = Integer.MIN_VALUE;
        int[][] dp = new int[A.length][A[0].length];
        dp[0][0] = A[0][0];

        for (int r = 0; r < A.length; r++) {
            for (int c = 0; c < A[0].length; c++) {
                if (r > 0 && c > 0) {
                    res = Math.max(res, A[r][c] - dp[r - 1][c - 1]);
                }
                dp[r][c] = A[r][c];
                if (r > 0) dp[r][c] = Math.min(dp[r][c], dp[r - 1][c]);
                if (c > 0) dp[r][c] = Math.min(dp[r][c], dp[r][c - 1]);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        MaxAcdAab solution = new MaxAcdAab();
        int[][] A = new int[][] {
                {1, 3, 5},
                {1, 3, 5},
                {2, 4, 6}
        };
        Display.myPrint(solution.maxAcdAab(A));
    }
}
