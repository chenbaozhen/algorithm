package algorithm.unionFind;

import algorithm.myUtility.MyPrintArray;

import java.util.Arrays;

public class BrickFalling {
    /*
    1000
    1110
     */
    public static void main(String[] args) {
        BrickFalling solution = new BrickFalling();
        int[][] bricks = new int[][]{
                {1,0,0,1},
                {1,1,0,1}
            };
        int[][] hits = new int[][]{{1, 0}, {0, 3}};
        System.out.println(Arrays.toString(solution.brickFalling(bricks, hits)));
    }

    public int[] brickFalling(int[][] bricks, int[][] hits) {
        int n = bricks.length;
        int m = bricks[0].length;
        int[][] bricksLarge = new int[n + 1][m];
        Arrays.fill(bricksLarge[0], 1);


        for (int row = 1; row <= n; row++) {
            for (int col = 0; col < m; col++) {
                bricksLarge[row][col] = bricks[row - 1][col];
            }
        }
        for (int[] hit : hits) {
            bricksLarge[hit[0] + 1][hit[1]] = 0;
        }

        UnionFind uf = new UnionFind((n + 1) * m );
        for (int row = 0; row <= n; row++) {
            for (int col = 0; col < m; col++) {
                if (bricksLarge[row][col] == 1) {
                    if (row > 0 && bricksLarge[row - 1][col] == 1) {
                        uf.union(row * m + col, (row - 1) * m + col);
                    }
                    if (col > 0 && bricksLarge[row][col - 1] == 1) {
                        uf.union(row * m + col, row * m + col - 1);
                    }
                }
            }
        }
        MyPrintArray.myPrintArray(bricksLarge);
        int[] result = new int[hits.length];
        int index = 0;
        int[][] OFFSETS = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        for (int i = hits.length - 1; i >= 0; i--) {
            int[] hit = hits[i];
            int count = 0;
            int oldGroup = uf.root(0);
            for (int[] os : OFFSETS) {
                int tRow = os[0] + hit[0] + 1;
                int tCol = os[1] + hit[1];
                if (tRow < 1 || tCol < 1 || tRow > bricksLarge.length || tCol >= bricksLarge[0].length) {
                    continue;
                }
                if (bricksLarge[tRow][tCol] == 0) {
                    continue;
                }
                bricksLarge[hit[0] + 1][hit[1]] = 1;
                uf.union((hit[0] + 1) * m + hit[1], tRow * m + tCol);
            }
            result[i] = uf.root(0) - oldGroup;

        }
        return result;

    }
    class UnionFind{
        int[] ids;
        int[] size;
        int count;
        public UnionFind(int n) {
            ids = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
            }
            Arrays.fill(size, 1);
        }
        private int root(int a) {
            int aRoot = a;
            while (ids[aRoot] != aRoot) {
                aRoot = ids[aRoot];
            }
            while (a != aRoot) {
                int next = ids[a];
                ids[a] = aRoot;
                a = next;
            }
            return aRoot;
        }
        public void union(int a, int b) {
            int aRoot = root(a);
            int bRoot = root(b);
            if (aRoot == bRoot) return;
            if (size[aRoot] >= size[bRoot]) {
                ids[bRoot] = aRoot;
                size[aRoot] += size[bRoot];

            } else {
                ids[aRoot] = bRoot;
                size[bRoot] += size[aRoot];
            }
            count--;
        }
        public boolean find(int a, int b) {
            return root(a) == root(b);
        }
    }
}
