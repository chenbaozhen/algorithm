package algorithm.unionFind;

import java.util.*;

public class AccountMerge {

    public List<List<String>> merge(List<List<String>> accounts) {
        Map<String, String> emailName = new HashMap<>();
        Map<String, Integer> emailToId = new HashMap<>();
        int k = 0;
        for (List<String> list : accounts ) {
            String name = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                if (!emailName.containsKey(list.get(i))) {
                    emailName.put(list.get(i), name);
                    emailToId.put(list.get(i), k++);
                }
            }
        }

        UnionFind uf = new UnionFind(k);

        for (List<String> list : accounts) {
            if (list.size() <= 2) continue;
            String name = list.get(0);
            int id1 = emailToId.get(list.get(1));
            for (int i = 2; i < list.size(); i++) {
                uf.union(id1, emailToId.get(list.get(i)));
            }
        }

        Map<Integer, Set<String>> groups = new HashMap<>();
        for (String email : emailToId.keySet()) {
            int root = uf.root(emailToId.get(email));
            Set<String> set2 = groups.get(root);
            if (set2 == null) {
                set2 = new HashSet<>();
            }
            set2.add(email);
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : groups.entrySet()) {
            List<String> list3 = new ArrayList<>();
            list3.add(emailName.get(entry.getValue().iterator().next()));
            list3.addAll(entry.getValue());
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
