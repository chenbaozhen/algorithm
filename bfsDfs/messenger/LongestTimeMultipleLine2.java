package algorithm.bfsDfs.messenger;

import com.sun.xml.internal.ws.api.addressing.AddressingVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestTimeMultipleLine2 {
    public int longestTimeMultipleLine2(EmployeeNode CEO) {
        Map<EmployeeNode, List<EmployeeNode>> map = new HashMap<>();
        addNode(map, CEO);
        int[] longest = new int[1];
        Map<EmployeeNode, Integer> best = new HashMap<>();
        best.put(CEO, 0);
        for (EmployeeNode ep : map.keySet()) {
            int localBest = findBest(ep, best, map);
            longest[0] = Math.max(localBest, longest[0]);
        }
        return longest[0];

    }
    private int findBest(EmployeeNode ep, Map<EmployeeNode, Integer> best, Map<EmployeeNode, List<EmployeeNode>> map) {
        if (best.containsKey(ep)) return best.get(ep);
        int candidate = Integer.MAX_VALUE;
        for (EmployeeNode boss : map.get(ep)) {
            candidate = Math.min(candidate, findBest(boss, best, map) + boss.cost);
        }
        best.put(ep, candidate);
        return candidate;
    }
    private void addNode(Map<EmployeeNode, List<EmployeeNode>> map, EmployeeNode boss) {
        for (EmployeeNode ep : boss.directReports) {
            List<EmployeeNode> list = map.get(ep);
            if (list == null) {
                list = new ArrayList<>();
                map.put(ep, list);
            }
            list.add(boss);
            addNode(map, ep);
        }
    }
}
