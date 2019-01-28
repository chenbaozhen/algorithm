package algorithm.dp;

public class UnicodeDelete {
    boolean isOneByte(int[] input) {
        if (input[input.length - 1] == 1) return false;
        boolean[] dp = new boolean[input.length];
        dp[input.length - 1] = true;

        for (int index = input.length - 2; index >= 0; index--) {
            if (input[index] == 1) {
                if (index < input.length - 2) {
                    dp[index] = dp[index + 2];
                } else {
                    dp[index] = false;
                }

            } else {
                dp[index] = dp[index + 1];
            }
        }
        return dp[0];
    }

    boolean isOneByte2(int[] input) {
        if (input[input.length - 1] == 1) return false;
        boolean[] dp = new boolean[input.length];
        dp[input.length - 1] = true;

        for (int index = input.length - 2; index >= 0; index--) {
            if (input[index] == 1) {
                if (index < input.length - 2) {
                    dp[index] = dp[index + 2];
                } else {
                    dp[index] = false;
                }

            } else {
                dp[index] = dp[index + 1];
                return dp[index];
            }
        }
        return dp[0];
    }
    public static void main(String[] args) {
        UnicodeDelete solution = new UnicodeDelete();
        System.out.println(solution.isOneByte2(new int[]{0, 1,0, 0, 1, 0}));
    }
}
