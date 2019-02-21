package algorithm.binarySearch;

import java.util.Arrays;
import java.util.List;

public class LocalMin2D {
    public int localMin(int[][] matrix) {
        // not checked for corner case
        int t = 0, b = matrix.length - 1;
        while (t < b) {
            int mid = t + (b - t) / 2;
            int rowMin = matrix[mid][0];
            int rowMinCol = 0;
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[mid][c] < rowMin) {
                    rowMin = matrix[mid][c];
                    rowMinCol = c;
                }
            }
            if (matrix[mid][rowMinCol] < matrix[mid + 1][rowMinCol]) {
                t = mid + 1;
            } else if (matrix[mid][rowMinCol] < matrix[mid - 1][rowMinCol]){
                b = mid - 1;
            } else {
                return rowMin;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        LocalMin2D solution = new LocalMin2D();
        int[][] A = new int[][] {
                {1,3,2},{4,6,5},{7,9,8},{13,15,14},{10,12,11}
        };
        System.out.println(solution.findPeakII(A));

    }
    public List<Integer> findPeakII(int[][] A) {
        // write your code here
        int N = A.length, M = A[0].length;
        int t = 0, b = N - 1, l = 0, r = M - 1;
        while (t  < b && l  < r) {
            System.out.println("t = " + t + "b = " + b + "l = " + l + "r = " + r);
            if ((b-t) > (r - l)) {
                int midRow = t + (b - t) / 2;
                int maxC = findRowMax(A, midRow);
                if (A[midRow + 1][maxC] < A[midRow][maxC] && A[midRow - 1][maxC] < A[midRow][maxC]) {
                    return Arrays.asList(midRow, maxC);
                } else if (A[midRow + 1][maxC] > A[midRow][maxC]) {
                    t = midRow + 1;
                    System.out.println("t = " + t);
                } else {
                    b = midRow - 1;
                    System.out.println("b = " + b);
                }
            } else {
                int midCol = l + (r - l) / 2;
                int maxR = findColMax(A, midCol);
                if (A[maxR][midCol + 1] < A[maxR][midCol] && A[maxR][midCol - 1] < A[maxR][midCol]) {
                    return Arrays.asList(maxR, midCol);
                } else if (A[maxR][midCol + 1] > A[maxR][midCol]) {
                    l = midCol ;
                } else {
                    r = midCol ;
                }
            }
        }
        return Arrays.asList(0, 0);
    }
    private int findRowMax(int[][] A, int row) {
        int maxC = 0;
        for (int c = 0; c < A[0].length; c++) {
            if (A[row][c] > A[row][maxC]) {
                maxC = c;
            }
        }
        return maxC;
    }
    private int findColMax(int[][] A, int col) {
        int maxR = 0;
        for (int r = 0; r < A.length; r++) {
            if (A[r][col] > A[maxR][col]) {
                maxR = r;
            }
        }
        return maxR;
    }
}
