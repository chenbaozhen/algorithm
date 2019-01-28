package algorithm.dp;


public class LargestSquareOf1 {
    public static void main(String[] args) {
        LargestSquareOf1 solution = new LargestSquareOf1();
        int[][] m = new int[][] {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0}
        };
        System.out.println(solution.largestSquareOfOnes(m));
    }
    public int largestSquareOfOnes(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rNumber = matrix.length, cNumber = matrix[0].length;
        int[][] dp = new int[rNumber][cNumber];
        int globalMax = 0;
        for (int r = 0; r < rNumber; r++) {
            for (int c = 0; c < cNumber; c++) {
                if (r == 0 || c == 0) {
                    dp[r][c] = matrix[r][c];
                    continue;
                }
                if (matrix[r][c] == 1) {
                    dp[r][c] = Math.min( Math.min(dp[r - 1][c], dp[r][c - 1]), dp[r - 1][c - 1]) + 1;
                    globalMax = Math.max(dp[r][c], globalMax);
                }
            }
        }

        return globalMax;
    }
}
