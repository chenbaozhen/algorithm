package algorithm.miscs.Jan262019;

import algorithm.myUtility.Display;

public class Games {
    public static void main(String[] args) {
        Games solution = new Games();
        Display.myPrint(solution.canIWinWithReplacement(3, 8));
        Display.myPrint(solution.canIWinWithReplacement(3, 1));
    }
    public boolean canIWinWithReplacement(int T, int W) {
        int[] dp = new int[W + 1];
        dp[W] = 2;
        return canIWinWithReplacement(T, W, 0, dp);
    }
    private boolean canIWinWithReplacement(int T, int W, int cur, int[] dp) {
        if (cur >= W) return false;
        if (W >= cur + 1 && W <= cur + T) {
            dp[cur] = 1;
            return true;
        }
        if (dp[cur] != 0) {
            return dp[cur] == 1;
        }
        for (int i = 1; i <= T; i++) {
            if (!canIWinWithReplacement(T, W, cur + i, dp)) {
                dp[cur] = 1;
                return true;
            }
        }
        dp[cur] = 2;
        return false;
    }
}
