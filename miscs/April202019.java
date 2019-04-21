package algorithm.miscs;

import algorithm.myUtility.Display;

import java.util.*;

public class April202019 {
    public boolean canFormPalin(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                set.remove(c);
            }
        }
        return set.size() < 2;
    }

    public int findWays(int m, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        if (m >= n) return 0;
        findWaysHelper(m, n, map);
        return map.get(m);
    }
    private int findWaysHelper(int m, int n, Map<Integer, Integer> map){
        if (m == n) return 1;
        if (map.containsKey(m)) return map.get(m);
        int count = 0;
        for (int d = 1; m + d <= n; d *= 2 ) {
            count += findWaysHelper(m + d, n, map);
        }
        map.put(m, count);
        return count;
    }


    public static void main(String[] args) {
        April202019 solution = new April202019();
        Display.myPrint(solution.findWays(1, 7));
    }
}

