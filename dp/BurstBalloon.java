package algorithm.dp;

import java.util.Arrays;

public class BurstBalloon {
    // Two methods

    public static void main(String[] args) {
        BurstBalloon solution = new BurstBalloon();
        System.out.println(solution.burstBallon2(new int[] {3, 1, 5, 8}));
    }
    public int burstBallon2(int[] ballons ) {
        if (ballons == null || ballons.length == 0) return 0;
        int[] extend = new int[ballons.length + 2];
        Arrays.fill(extend, 1);
        for (int i = 0; i < ballons.length; i++) {
            extend[i + 1] = ballons[i];
        }
        int[][] dp = new int[extend.length][extend.length];
        for (int len = 1; len <= extend.length-2; len++) {
            for (int l = 1; l + len - 1 < extend.length-1; l++) {
                for (int k = l; k <= l + len - 1; k++) {
                    dp[l][l + len - 1] = Math.max(dp[l][l + len - 1],
                            extend[l - 1] * extend[l + len] * extend[k] +
                                    dp[l][k - 1] + dp[k + 1][l + len - 1]
                            );
                }
            }
        }
        return dp[1][extend.length - 2];
    }

    public int burstBallon(int[] ballons) {
        if (ballons == null || ballons.length == 0) return 0;
        int[] extended = new int[ballons.length + 2];
        Arrays.fill(extended, 1);
        for (int i = 0; i < ballons.length; i++) {
            extended[i + 1] = ballons[i];
        }

        int[][] dp = new int[extended.length][extended.length];
        for (int r = 1; r < extended.length - 1; r++) {
            for (int l = r; l >= 1; l-- ) {
                if (l == r ) {
                    dp[l][r] = extended[l] * extended[l - 1] * extended[l + 1];
                } else {
                    int best = 0;
                    for (int k = r; k >= l; k-- ) {
                        int localBest = (l <= k - 1 ? dp[l][k - 1] : 0) +
                                (k+ 1 <= r ? dp[k + 1][r] : 0) + extended[k] * extended[l - 1] * extended[r + 1];
                        best = Math.max(best, localBest);
                    }
                    dp[l][r] = best;
                }
            }
        }
        return dp[1][dp.length - 2];
    }


}
