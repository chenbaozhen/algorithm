package algorithm.bfsDfs.messenger;

import java.util.*;

public class BFSIteratorMultipleLine {

    Set<EmployeeNode> expanded;
    Queue<Tuple> queue;
    BFSIteratorMultipleLine(EmployeeNode CEO) {
        expanded = new HashSet<>();
        queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.prevCost));
        queue.offer(new Tuple(CEO, 0));
    }
    public boolean hasNext(){
        while (!queue.isEmpty() && expanded.contains(queue.peek().node)) {
            queue.poll();
        }
        return !queue.isEmpty();
    }

    public EmployeeNode next(){
        Tuple t = queue.poll();
        for (EmployeeNode dR : t.node.directReports) {
            queue.offer(new Tuple(dR, t.prevCost + t.node.cost));
        }
        return t.node;
    }

    private class Tuple{
        EmployeeNode node;
        int prevCost;
        Tuple(EmployeeNode node, int prevCost) {
            this.node = node;
            this.prevCost = prevCost;
        }

    }
}
