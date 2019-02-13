package algorithm.SegmentTree;

public class LC308 {
    public static void main(String[] args) {


        NumMatrix solution = new NumMatrix(new int[][]{
                {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        });
        solution.sumRegion(2,1,4,3);

    }

}

class NumMatrix {
    SegmentTreeNode root;
    int[][] matrix;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int N = matrix.length, M = matrix[0].length;
        this.matrix = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                this.matrix[r][c] = matrix[r][c];
            }
        }
        root = buildTree(matrix, 0, 0, N - 1, M - 1);
    }
    private SegmentTreeNode buildTree(int[][] matrix, int row1, int col1, int row2, int col2) {
        if (row1 > row2 || col1 > col2) return null;
        if (row1 == row2 && col1 == col2) {
            return new SegmentTreeNode(row1, col1,row2, col2, matrix[row1][col1]);
        }
        int midRow = row1 + (row2 - row1) / 2;
        int midCol = col1 + (col2 - col1) /2;
        SegmentTreeNode node = new SegmentTreeNode(row1, col1, row2,  col2, 0);
        node.c1 = buildTree(matrix, row1,  col1, midRow, midCol);
        node.c2 = buildTree(matrix, row1, midCol + 1, midRow, col2);
        node.c3 = buildTree(matrix, midRow + 1, col1,  row2,  midCol);
        node.c4 = buildTree(matrix, midRow + 1, midCol + 1, row2, col2);
        node.sum =  (node.c1 == null ? 0 : node.c1.sum) +
                (node.c2 == null ? 0 : node.c2.sum) + (node.c3 == null ? 0 : node.c3.sum)
                + (node.c4 == null ? 0 : node.c4.sum);
        return node;
    }
    private void updateHelper(SegmentTreeNode node, int row, int col, int val) {
        if (node == null || row < node.row1 || row > node.row2 || col < node.col1 || col > node.col2) return;
        if (row == node.row1 && row == node.row2 && col == node.col1 && col == node.col2) {
            node.sum = val;
            return;
        }
        updateHelper(node.c1, row, col, val);
        updateHelper(node.c2, row, col, val);
        updateHelper(node.c3, row, col, val);
        updateHelper(node.c4, row, col, val);
        node.sum = (node.c1 == null ? 0 : node.c1.sum) +
                (node.c2 == null ? 0 : node.c2.sum) + (node.c3 == null ? 0 : node.c3.sum)
                + (node.c4 == null ? 0 : node.c4.sum);
    }
    private int sumRegionHelper(SegmentTreeNode node, int row1, int col1, int row2, int col2) {
        if (node == null || row1 < node.row1 || row2 > node.row2 || col1 < node.col1 || col2 > node.col2) return 0;
        if (row1 == node.row1 && row2 == node.row2 && col1 == node.col1 && col2 == node.col2) {
            return node.sum;
        }
        return sumRegionHelper(node.c1, row1, col1, row2, col2) + sumRegionHelper(node.c2, row1, col1, row2, col2)
                + sumRegionHelper(node.c3, row1, col1, row2, col2) + sumRegionHelper(node.c4, row1, col1, row2, col2);
    }
    public void update(int row, int col, int val) {
        updateHelper(root, row, col, val);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumRegionHelper(root, row1, col1, row2, col2);
    }
    class SegmentTreeNode{
        int row1, row2, col1, col2, sum;
        SegmentTreeNode c1, c2, c3, c4;
        public SegmentTreeNode(int row1, int col1, int row2, int col2, int sum) {
            this.row1 = row1;
            this.row2 = row2;
            this.col1 = col1;
            this.col2 = col2;
            this.sum = sum;
        }
    }
}

