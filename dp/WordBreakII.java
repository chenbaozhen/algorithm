package algorithm.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        // a right solution but TLE
        Set<String> dict = new HashSet<>();
        for (String word: wordDict) {
            dict.add(word);
        }
        boolean[] canBreak = new boolean[s.length() + 1];
        List<List<String>> result = new ArrayList<>();
        canBreak[0] = true;
        result.add(new ArrayList<String>());
        result.get(0).add("");
        for (int i = 1; i <= s.length(); i++) {
            List<String> curList = new ArrayList<>();
            result.add(curList);
            for (int j = 1; j <= i; j++) {
                if (canBreak[j - 1] && dict.contains(s.substring(j - 1, i))) {
                    canBreak[i] = true;
                    List<String> list = result.get(j - 1);
                    if (j == 1) {
                        curList.add(s.substring(j - 1, i));
                    } else {
                        for (String str : list) {
                            curList.add(str + " " + s.substring(j - 1, i));
                        }
                    }
                }
            }
        }
        return result.get(s.length());
    }
}
