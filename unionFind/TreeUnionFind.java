package algorithm.unionFind;

public class TreeUnionFind {
    int[] ids;
    public TreeUnionFind(int n) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
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
        ids[bRoot] = aRoot;
    }
    public boolean find(int a, int b) {
        return root(a) == root(b);
    }
}
