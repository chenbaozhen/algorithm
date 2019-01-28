package algorithm.dp.Dec122018;

public class SumOfArbitrarySubmatrix {
    int[][] dp;
    public SumOfArbitrarySubmatrix(int[][] A) {
        dp = new int[A.length][A[0].length];
        for (int r = 0; r < A.length; r++) {
            for (int c = 0; c < A[0].length; c++) {
                dp[r][c] = A[r][c];
                if (r > 0) dp[r][c] += dp[r - 1][c];
                if (c > 0) dp[r][c] += dp[r][c - 1];
                if (r > 0 && c > 0) dp[r][c] -= dp[r - 1][c - 1];
            }
        }
    }
    public int sumMatrixSum(int i, int j, int k, int l) {

        if (i > k) {
            int t = i;
            i = k;
            k = t;
        }
        if ( j > l) {
            int t = j;
            j = l;
            l = t;
        }
        return dp[k][l] -dp[k][j] - dp[i][l] + dp[i][j];
    }
}
