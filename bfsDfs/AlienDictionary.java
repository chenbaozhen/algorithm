package algorithm.bfsDfs;

import java.util.*;

public class AlienDictionary {
    public long numberOfAlienOrder(String[] words) {
        int[][] graph = new int[26][26];
        int[] inDegrees = new int[26];

        buildGraph(words, graph, inDegrees);
        //StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();

        for (int i = 0; i < 26; i++ ) {
            if (inDegrees[i] == 0) {
                queue.offer((char) ('a' + i));
            }
        }
        Map<Integer, Integer> idMap = new HashMap<>();
        Map<Integer, Integer> letterToIdMap = new HashMap<>();
        int id = 0;
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] != - 1) {
                letterToIdMap.put(i, id); //方便查找。
                idMap.put(id++, i); //方便查找。
            }
        }
        long ans = 0L;
        int N = idMap.size();
        int totalState = 1 << N;
        long[][] dp = new long[N][totalState]; //最后一个字母是第i个字母，前面所有的state
        for (int state = 0; state < totalState; state++) {
            for (int j = 0; j < N; j++) {
                List<Integer> parent = getParent(graph, idMap, j, letterToIdMap);
                if (parent.size() == 0 && state == (1 << j)) { //initialize
                    dp[j][state] = 1;
                    continue;
                }
                if ((state & (1 << j)) == 0) continue;
                //if state contains all parents, dp[j][state] = sum (dp[*][state - (1<<j)];
                for (int p : parent) {
                    if ((state & (1 << p)) == 0) continue; //parent are not all present, not valid
                }
                for (int i = 0; i < N; i++) {
                    dp[j][state] += dp[i][state - (1 << j)];
                }
            }
        }
        for (int i = 0; i < N; i++) {
            ans += dp[i][totalState - 1];
        }

        return ans;

    }
    private List<Integer> getParent(int[][] graph, Map<Integer, Integer> idMap, int child, Map<Integer, Integer> letterToIdMap) {
        List<Integer> parents = new ArrayList<>();
        int childLetter = idMap.get(child);
        for (int i = 0; i < 26; i++) {
            if (graph[i][childLetter] != 0) {
                parents.add(letterToIdMap.get(i));
            }
        }
        return parents;
    }
    public String alienOrder(String[] words) {
        int[][] graph = new int[26][26];
        int[] inDegrees = new int[26];

        buildGraph(words, graph, inDegrees);
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();

        for (int i = 0; i < 26; i++ ) {
            if (inDegrees[i] == 0) {
                queue.offer((char) ('a' + i));
            }
        }
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            sb.append(ch);
            for (int i = 0; i < 26; i++) {
                if (graph[ch - 'a'][i] == 1) {
                    inDegrees[i]--;
                    if (inDegrees[i] == 0) {
                        queue.offer((char) ('a' + i));
                    }
                }
            }
        }
        for (int n : inDegrees) {
            if (n > 0) return "";
        }
        return sb.toString();
    }
    private void buildGraph(String[] words, int[][] graph, int[] inDegrees) {
        Arrays.fill(inDegrees, -1);
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray())
                inDegrees[c - 'a'] = 0;
            if (i < words.length - 1) {
                int[] order = findOrder(words[i], words[i + 1]);
                if (order == null) continue;
                graph[order[0]][order[1]] = 1;
            }
        }
        for (int j = 0; j < 26; j++) {
            if (inDegrees[j] == -1) continue;
            for (int i = 0; i < 26; i++) {
                inDegrees[j] += graph[i][j];
            }
        }
    }
    private int[] findOrder(String w1, String w2) {
        int pt1 = 0;
        while (pt1 < w1.length() && pt1 < w2.length()) {
            if (w1.charAt(pt1) != w2.charAt(pt1)) {
                return new int[]{w1.charAt(pt1) - 'a', w2.charAt(pt1) - 'a'};
            }
            pt1++;
        }
        return null;
    }
}
