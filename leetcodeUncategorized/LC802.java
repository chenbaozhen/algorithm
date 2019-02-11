package algorithm.leetcodeUncategorized;
import algorithm.myUtility.Display;

import java.util.*;
public class LC802 {

    public static void main(String[] args) {
        LC802 solution = new LC802();
        int[][] graph = new int[][]{ {1,2,3,4},{1, 2},{3,4},{0,4},{}};
        System.out.println(solution.eventualSafeNodes(graph));

    }
    public List<Integer> eventualSafeNodes(int[][] graph) {

        int N = graph.length;
        int[] hasCycle = new int[N];
        int[] isTerminal = new int[N];

        Set<Integer> visiting = null;
        for (int i = 0; i < N; i++) {
            if (hasCycle[i] == 1 || isTerminal[i] == 1) continue;
            if(graph[i].length == 0) {
                isTerminal[i] = 1;
                continue;
            }
            visiting = new HashSet<>();
            dfsFoundCycle(i, graph, visiting,  hasCycle, isTerminal) ;
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            if (isTerminal[i] == 1) ans.add(i);
        }
        return ans;
    }

    private boolean dfsFoundCycle(int i, int[][] graph, Set<Integer> visiting,
                                  int[] hasCycles, int[] isTerminal) {
        if (hasCycles[i] == 1)
            return true;
        if (isTerminal[i] == 1) {
            return false;
        }
        if (visiting.contains(i)) {
            for (int n : visiting) {
                hasCycles[n] = 1;
                isTerminal[n] = 0;
            }
            return true;
        }
        if (!visiting.add(i)) System.out.println("error");
        for (int next : graph[i]) {
            if (dfsFoundCycle(next, graph, visiting, hasCycles, isTerminal)) {
                //hasCycles[next] = 1;
                hasCycles[i] = 1;
                break;
            }
        }
        if (hasCycles[i] == 0) {
            isTerminal[i] = 1;
            if (i == 2) {
                System.out.println(visiting);
                System.out.println(Arrays.toString(hasCycles));
                System.out.println(Arrays.toString(isTerminal));
            }
        }
        visiting.remove(i);
        return hasCycles[i] == 1;
    }

}
