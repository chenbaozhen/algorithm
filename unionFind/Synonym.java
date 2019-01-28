package algorithm.unionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Synonym {
    Map<String, Integer> indexes;
    UnionFind uf;
    public Synonym(String[] words) {
        uf = new UnionFind(words.length);
        int id = 0;
        for (String w : words) {
            uf.ids[id] = id;
            indexes.put(w, id++);
        }

    }
    public void associate(String w1, String w2){
        uf.union(indexes.get(w1), indexes.get(w2));
    }

    public boolean isSynonym(String w1, String w2) {
        return uf.find(indexes.get(w1), indexes.get(w2));
    }
    public List<String> getAllSynonym(String w1) {
        List<String> result = new ArrayList<>();
        int root = uf.root(indexes.get(w1));
        for (String w : indexes.keySet()) {
            if (w != w1 && uf.root(indexes.get(w)) == root) {
                result.add(w);
            }
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
        public int root(int a) {
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
