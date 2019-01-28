package algorithm.bfsDfs.messenger;


import java.util.*;

public class LongestTimeMultipleLine {
    public int longestTimeMultipleLine(EmployeeNode boss){
        Set<EmployeeNode> expanded = new HashSet<>();
        Queue<Tuple> minHeap = new PriorityQueue<>(Comparator.comparingInt(t -> t.prevCost));
        minHeap.offer(new Tuple(boss, 0));
        int max = 0;
        while (!minHeap.isEmpty()) {
            Tuple t = minHeap.poll();
            if (!(expanded.add(t.node))) {
                continue;
            }
            List<EmployeeNode> dR = t.node.directReports;
            for (EmployeeNode e : dR) {
                minHeap.offer(new Tuple(e, t.prevCost + t.node.cost));
            }
            max = Math.max(t.prevCost + t.node.cost, max);
        }
        return max;
    }
    private class Tuple{
        EmployeeNode node;
        int prevCost;
        private Tuple(EmployeeNode node, int prevCost) {
            this.node = node;
            this.prevCost = prevCost;
        }
    }

}
