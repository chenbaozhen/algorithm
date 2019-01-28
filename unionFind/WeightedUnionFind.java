package algorithm.unionFind;

import java.util.Arrays;

public class WeightedUnionFind {
    int[] ids;
    int[] size;
    public WeightedUnionFind(int n) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        Arrays.fill(size, 1);
    }
    private int root(int a) {
        while (ids[a] != a) {
            a = ids[a];
        }
        return a;
    }
    public void union(int a, int b) {
        int aRoot = root(a);
        int bRoot = root(b);
        if (size[aRoot] >= size[bRoot]) {
            ids[bRoot] = aRoot;
            size[aRoot] += size[bRoot];
        } else {
            ids[aRoot] = bRoot;
            size[bRoot] += size[aRoot];
        }
    }
    public boolean find(int a, int b) {
        return root(a) == root(b);
    }
}
