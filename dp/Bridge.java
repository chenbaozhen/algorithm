package algorithm.dp;

public class Bridge {
    public int maxBridges(int[] c1, int[] c2) {

        int[][] dp = new int[c1.length + 1][c2.length + 1];

        /*
        dp[i][j] the number of bridges with first i and first j city
        dp[i][j] =
            if c1[i] == c2[j] : 1 + dp[i - 1][j - 1];
            else : Math.max(dp[i - 1][j], dp[i][j - 1]

            x x x x
            x x x x
            x x x x
            x x x x

         */
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                if (c1[i] == c2[j]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[c1.length][c2.length];
    }
}
