package algorithm.unionFind;

import java.util.Arrays;

public class DistintIslandI {
    public static void main(String[] args) {
        DistintIslandI dI1 = new DistintIslandI();
        int[][] m = new int[][] {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {1,0,1,0,0},
                {1,0,1,0,1}
        };
        System.out.println(dI1.distinctIslandI(m));
    }

    public int distinctIslandI(int[][] matrix) {
        int rowMax = matrix.length;
        int colMax = matrix[0].length;
        UnionFind uf = new UnionFind(rowMax * colMax);
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {
                uf.count += matrix[row][col];
                if (matrix[row][col] == 0) continue;
                if (row > 0 && matrix[row - 1][col] == 1) {
                    uf.union(row * colMax + col, (row - 1) * colMax + col );
                }
                if (col > 0 && matrix[row][col - 1] == 1) {
                    uf.union(row * colMax + col, row * colMax + col - 1);
                }
            }
        }
        return uf.count;
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


