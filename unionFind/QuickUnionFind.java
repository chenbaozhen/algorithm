package algorithm.unionFind;

public class QuickUnionFind {
    int[] ids;
    QuickUnionFind(int n ) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public void union(int a, int b) {
        // O(N)
        int aG = ids[a];
        int bG = ids[b];
        if (aG == bG) return;
        for (int i = 0; i < ids.length; i++) {
           ids[i] = (ids[i] == bG) ? aG : ids[i];
        }
    }

    public boolean find(int a, int b) {
        // O(1)
        return ids[a] == ids[b];
    }
}
