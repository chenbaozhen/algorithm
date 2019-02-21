package algorithm.leetcodeUncategorized;

import java.util.*;

public class LC745 {
    public static void main(String[] args) {
        WordFilter solution = new WordFilter(new String[]{"pop"});
        System.out.println(solution.f("", "op"));
    }
}

class WordFilter {
    TrieNode root;
    public WordFilter(String[] words) {
        root = new TrieNode('a', 'a');
        for (int i = 0; i < words.length; i++) {
            addToTrie(root, words[i], 0, words[i].length() - 1, i);
        }
    }

    public int f(String prefix, String suffix) {
        String postfix = (new StringBuilder(suffix)).reverse().toString();
        return find(prefix, 0, postfix, 0, root);
    }
    private void addToTrie(TrieNode root, String w, int l, int r, int id) {
        if (l == w.length()) {
            root.strId = id;
            return;
        }
        char cl = w.charAt(l), cr = w.charAt(r);
        int code = (cl - 'a') * 26 + (cr - 'a');
        TrieNode next = root.getOrCreate(code);
        addToTrie(next, w, l + 1, r - 1, id);
    }
    private int find(String prefix, int lIndex, String postfix, int rIndex, TrieNode root) {
        if (root == null) return -1;
        Character cl = lIndex < prefix.length() ? prefix.charAt(lIndex) : null;
        Character cr = rIndex < postfix.length() ? postfix.charAt(rIndex) : null;
        if (cl != null && cr != null) {
            int code = (cl - 'a') * 26 + (cr - 'a');
            if (!root.hasChild(code)) return -1;
            return find(prefix, lIndex + 1, postfix, rIndex + 1, root.getChild(code));
        } else if (cr == null && cl != null) {
            Queue<Integer> ans = new PriorityQueue<>(Collections.reverseOrder());
            for (int code = (cl - 'a') * 26; code < (cl - 'a') * 26 + 26; code++) {
                ans.offer(find(prefix, lIndex + 1, postfix, rIndex, root.getChild(code)));
            }
            return ans.peek();

        } else if (cl == null && cr != null) {
            Queue<Integer> ans = new PriorityQueue<>(Collections.reverseOrder());
            for (int code = (cr - 'a'); code < 26 * 26 + (cr - 'a'); code += 26) {
                ans.offer(find(prefix, lIndex, postfix, rIndex + 1, root.getChild(code)));
            }
            return ans.peek();
        } else {
            Queue<Integer> ans = new PriorityQueue<>(Collections.reverseOrder());
            if (root.strId != -1) ans.offer(root.strId);
            for (int code = 0; code < 26 * 26 ; code ++) {
                ans.offer(find(prefix, lIndex, postfix, rIndex, root.getChild(code)));
            }
            return ans.peek();
        }
    }


}
class TrieNode {
    char pre;
    char post;
    int strId;
    Map<Integer, TrieNode> children;
    public TrieNode(char pre, char post) {
        this.pre = pre;
        this.post = post;
        children = new HashMap<>();
        strId = -1;
    }
    public TrieNode getOrCreate(int code) {
        if (children.get(code) == null) {
            children.put(code, new TrieNode((char) ('a' + code / 26) , (char) ('a' + (code % 26) )));
        }
        return children.get(code);
    }
    public TrieNode getChild(int code) {
        return children.get(code);
    }
    public boolean hasChild(int code) {
        return children.containsKey(code);
    }
}