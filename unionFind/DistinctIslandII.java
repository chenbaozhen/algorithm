package algorithm.unionFind;


import java.util.Arrays;

public class DistinctIslandII {
    public static void main(String[] args) {
        DistinctIslandII dI2 = new DistinctIslandII();

    }

    public int[] distinctIslandII(int m, int n, int[] x, int[] y) {
       UnionFind uf = new UnionFind(m * n);
       int[] result = new int[x.length];
       int[][] matrix = new int[m][n];
       for (int i = 0; i < x.length; i++) {
           if (matrix[x[i]][y[i]] == 1) {
               result[i] = uf.count;
               continue;
           }
           matrix[x[i]][y[i]] = 1;
           uf.count++;
           if (x[i] > 0 && matrix[x[i] - 1][y[i]] == 1) {
               uf.union((x[i] - 1) * n + y[i], x[i] * n + y[i] );
           }

           if (y[i] > 0 && matrix[x[i] ][y[i] - 1] == 1) {
               uf.union((x[i] ) * n + y[i] - 1, x[i] * n + y[i] );
           }
           result[i] = uf.count;
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

