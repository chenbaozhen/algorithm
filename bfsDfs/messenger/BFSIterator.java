package algorithm.bfsDfs.messenger;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFSIterator {
    Queue<Tuple> minHeap;
    public BFSIterator(EmployeeNode CEO) {
        minHeap = new PriorityQueue<>(Comparator.comparingInt(T -> T.prevCost));
        minHeap.offer(new Tuple(CEO, 0));
    }

    public EmployeeNode next() {
        if (!hasNext()) return null;
        Tuple t = minHeap.poll();
        List<EmployeeNode> dR = t.node.directReports;
        if (dR != null && dR.size() != 0 ) {
            int curCost = t.prevCost + t.node.cost;
            for (EmployeeNode e : dR) {
                minHeap.offer(new Tuple(e, curCost));
            }
        }
        return t.node;
    }
    public boolean hasNext(){
        if (minHeap.isEmpty()) return false;
        return true;
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
