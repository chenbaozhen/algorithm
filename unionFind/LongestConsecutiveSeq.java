package algorithm.unionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSeq {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> mapToIds = new HashMap<>();
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!mapToIds.containsKey(nums[i])) {
                mapToIds.put(nums[i], k++);
            }
        }
        UnionFind uf = new UnionFind(k);
        for (Map.Entry<Integer, Integer> entry : mapToIds.entrySet()) {
            int n = entry.getKey();
            if (mapToIds.containsKey(n - 1)) {
                uf.union(n - 1, n);
            }
            if (mapToIds.containsKey(n + 1)) {
                uf.union(n + 1, n);
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
