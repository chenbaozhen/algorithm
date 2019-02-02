package algorithm.leetcodeUncategorized;

import java.util.*;

public class lc269AlienDictionary {
    public String alienOrder(String[] words) {

        //Get a collection of characters used.
        boolean[] appeared = new boolean[26];
        for (String word : words) {
            for (char c : word.toCharArray()) appeared[c - 'a'] = true;
        }

        // construct Graph
        int[] inDegrees = new int[26];
        List<Set<Character>> downStreams = new ArrayList<>();

        for (int i = 0; i < 26; i++) downStreams.add(new HashSet<>());
        for (int i = 0; i < words.length - 1; i++) {
            char[] edge = compareTwoWord(words[i], words[i + 1]);
            if (edge != null) {
                char u = edge[0], d = edge[1]; // u means up, d means down. u -> d
                if (downStreams.get(u - 'a').add(d)) {
                    inDegrees[d - 'a']++; // if the edge is not duplicated, inDegrees ++;
                }
            }
        }

        // topological sort
        List<Character> ans = new ArrayList<>();
        Queue<Character> queue = new LinkedList<>();
        int N = 0; // N means how many unique charaters are there

        for (int i = 0; i < 26; i++) {
            if (inDegrees[i] == 0 && appeared[i]) {
                queue.add((char) (i + 'a'));
                ans.add((char) (i + 'a'));
            }
            if (appeared[i]) N++;
        }
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            for (char downCh : downStreams.get(ch - 'a')) {
                inDegrees[downCh - 'a']--;
                if (inDegrees[downCh - 'a'] == 0) {
                    queue.add(downCh);
                    ans.add(downCh);
                }
            }
        }

        if (ans.size() != N) return "";
        StringBuilder sb = new StringBuilder();
        for (char ch : ans) sb.append(ch);
        return sb.toString();
    }
    private char[] compareTwoWord(String w1, String w2) {
        char[] ans = new char[2];
        for (int pt = 0; pt < w1.length() && pt < w2.length(); pt++) {
            if (w1.charAt(pt) != w2.charAt(pt)) {
                ans[0] = w1.charAt(pt);
                ans[1] = w2.charAt(pt);
                return ans;
            }
        }
        return null;
    }
}
