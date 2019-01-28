package algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    int min;
    Map<Integer, Integer> valueMap;
    Map<Integer, Integer> countMap;
    Map<Integer, LinkedHashSet<Integer>> lists;
    int MAXCAP;
    public LFUCache(int capacity) {
        MAXCAP = capacity;
        valueMap = new HashMap<>();
        countMap = new HashMap<>();
        lists = new HashMap<>();
        min = 1;
    }

    public int get(int key) {
        if (MAXCAP < 1) return -1;
        if (valueMap.get(key) == null) return -1;

        int count = countMap.get(key);
        countMap.put(key, count + 1);
        lists.get(count).remove(key);
        if (lists.get(count).size() == 0 ) {
            lists.remove(count);
            int dummy = (count == min ? min++ : 0);
        }
        lists.putIfAbsent(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
        return valueMap.get(key);
    }

    public void put(int key, int value) {
        if (MAXCAP < 1) return ;
        if (valueMap.containsKey(key)) {
            valueMap.put(key, value);
            get(key);
            return;
        }
        if (valueMap.size() < MAXCAP) {
            valueMap.put(key, value);
            countMap.put(key, 1);
            lists.putIfAbsent(1, new LinkedHashSet<>());
            lists.get(1).add(key);
            min = 1;
            return;
        } else {
            Iterator<Integer> it = lists.get(min).iterator();
            int keyToEvict = it.next();
            valueMap.remove(keyToEvict);
            lists.get(min).remove(keyToEvict);
            valueMap.put(key, value);
            countMap.put(key, 1);
            lists.putIfAbsent(1, new LinkedHashSet<>());
            lists.get(1).add(key);

            if (lists.get(min).size() == 0) {
                lists.remove(min);
            }
            min = 1;
            return;
        }
    }
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,1);

    }
}
