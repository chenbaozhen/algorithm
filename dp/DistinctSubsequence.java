package algorithm.dp;

public class DistinctSubsequence {
    public static void main(String[] args) {
        DistinctSubsequence solution = new DistinctSubsequence();
        String s = "rabbbit", t = "rabit";
        System.out.println(solution.distinctSubsequence(s, t));
        System.out.println(solution.distinctSubsequence("ABCDE", "ACE"));
    }

    public int distinctSubsequence(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for( int i = 0; i <= s.length(); i++) {

            for (int j = 0; j <= t.length(); j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] += dp[i - 1][j];
                    if(s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] += dp[i - 1][j - 1];
                    }
                }

            }
        }
        return dp[s.length()][t.length()];
    }
}
