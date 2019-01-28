package algorithm.bfsDfs.messenger;

public class Test {
    public static void main(String[] args) {
        // Name
        // cost,
        // report
        String[] names = new String[]{"A", "B", "C","D"};
        int[] costs = new int[]{2, 10, 15, 1};
        int[] reports = new int[] {2, 0, 1, 0};
        EmployeeNode CEO = generateTree(names, costs,reports);
        LongestTime solution = new LongestTime();
        int result = solution.longestTime(CEO);
        System.out.println(result);

        BFSIterator iter = new BFSIterator(CEO);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

    private static EmployeeNode generateTree(String[] names, int[] costs, int[] reports) {
        int[] pt = new int[1];
        return inOrderTraversal(names, costs, reports, pt);
    }
    private static EmployeeNode inOrderTraversal(String[] names, int[] costs, int[] reports, int[] pt) {
        if (pt[0] >= names.length) return null;
        EmployeeNode root = new EmployeeNode(names[pt[0]], costs[pt[0]]);
        int curPt = pt[0];
        pt[0] ++;
        for (int i = 0; i < reports[curPt]; i++ ) {
            root.directReports.add(inOrderTraversal(names, costs, reports, pt ));
        }
        return root;
    }
}
