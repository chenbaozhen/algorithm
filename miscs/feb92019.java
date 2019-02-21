package algorithm.miscs;

import algorithm.myUtility.Display;

import java.util.*;

public class feb92019 {


    public List<List<TreeNode>> allConnectedPath(TreeNode root, int target) {
        List<List<TreeNode>> finalAns = new ArrayList<>();
        allConnectedPathHelper(root, finalAns, target);
        return finalAns;
    }
    public void tmp(){


    }

    private Map<Integer, List<List<TreeNode>>> allConnectedPathHelper(TreeNode root, List<List<TreeNode>> finalAns, int target) {
        if (root == null) return new HashMap<>();
        Map<Integer, List<List<TreeNode>>> left = allConnectedPathHelper(root.left, finalAns, target);
        Map<Integer, List<List<TreeNode>>> right = allConnectedPathHelper(root.right, finalAns, target);

        //one side path, return this to parent
        Map<Integer, List<List<TreeNode>>> rootOneSidePaths = new HashMap<>(); // one side

        //CASE1 : root ONLY

        // root as a single point path
        if (root.val == target) {
            finalAns.add(Arrays.asList(root));
        }
        // add to return result;
        rootOneSidePaths.putIfAbsent(root.val, new ArrayList<>());
        rootOneSidePaths.get(root.val).add(Arrays.asList(root));

        // CASE2 : root and One Side
        // root and left Path, root and right Path
        for (Map<Integer, List<List<TreeNode>>> leftOrRight : new Map[]{left, right} ) {

            for (Map.Entry<Integer, List<List<TreeNode>>> entry : leftOrRight.entrySet()) {

                int sum = entry.getKey() + root.val;

                for (List<TreeNode> childLPath : entry.getValue()) {
                    List<TreeNode> currentLPath = new ArrayList<>();
                    currentLPath.add(root);
                    currentLPath.addAll(childLPath);

                    if (sum == target) finalAns.add(currentLPath); // add to final answer

                    // add to return result;
                    rootOneSidePaths.putIfAbsent(sum, new ArrayList<>());
                    rootOneSidePaths.get(sum).add(currentLPath);
                }
            }
        }

        //Case 3: two sides 人字形
        // root left and right path: Only update the final answer

        for (Map.Entry<Integer, List<List<TreeNode>>> entry : left.entrySet()) {
            int sum = entry.getKey() + root.val;
            if (!right.containsKey(target - sum)) continue;

            List<List<TreeNode>> rightPathList = right.get(target - sum);
            for (List<TreeNode> childLPath : entry.getValue()) {
                for (List<TreeNode> childRPath : rightPathList) {
                    List<TreeNode> currentPath = new ArrayList<>();
                    currentPath.addAll(childLPath); //加入左边一撇
                    currentPath.add(root); //加入root
                    currentPath.addAll(childRPath); //加入右边一捺
                    finalAns.add(currentPath); //更新final anser
                }
            }
        }

        return rootOneSidePaths;
    }

    public TreeNode buildTree(List<String> levelOrder){
        if (levelOrder == null || levelOrder.size() == 0) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = null;
        int pt = 0;
        while (!queue.isEmpty() || pt == 0) {
            if (pt == 0) {
                String str = levelOrder.get(pt++);
                if ("#".equals(str)) return root;
                root = new TreeNode(Integer.parseInt(str));
                queue.offer(root);
            } else {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (pt >= levelOrder.size()) return root;
                    String strLeft = levelOrder.get(pt++);
                    if (!"#".equals(strLeft)) {
                        node.left = new TreeNode(Integer.parseInt(strLeft));
                        queue.offer(node.left);
                    }
                    if (pt >= levelOrder.size()) return root;
                    String strRight = levelOrder.get(pt++);
                    if (!"#".equals(strRight)) {
                        node.right = new TreeNode(Integer.parseInt(strRight));
                        queue.offer(node.right);
                    }
                }
            }
        }
        return root;
    }



    int[][] OFFSETS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int largestCluster(char[][] array) {
        int ans = 0;
        if (array == null || array.length == 0 || array[0].length == 0) return ans;
        int N = array.length, M = array[0].length;
        boolean[][] visited = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                ans = Math.max(ans, explore(array, r, c, visited));
            }
        }
        return ans;
    }
    private int explore(char[][] array, int r, int c, boolean[][] visited) {
        if (visited[r][c] || array[r][c] != '1') return 0;
        visited[r][c] = true;
        int numberOfNodes = 1;
        for (int[] os : OFFSETS) {
            int nr = os[0] + r, nc = os[1] + c;
            if (nr < 0 || nc < 0 || nr >= array.length || nc >= array[0].length) {
                continue;
            }
            numberOfNodes += explore(array, nr, nc, visited);
        }
        return numberOfNodes;
    }

    public long maxDownSequences(int[] prices) {
        int N = prices.length;
        if (N < 3) return 0;
        long[] dp = new long [N];
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (prices[j] > prices[i]) {
                    dp[i]++;
                }
            }
        }
        long count = 0;
        for (int i = 2; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (prices[i] < prices[j] && dp[j] > 0) {
                    count += dp[j];
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        feb92019 solution = new feb92019();
        int[] prices = new int[]{5, 3, 4, 2, 1};
        Display.myPrint(solution.maxDownSequences(prices));
        Display.myPrint(solution.maxDownSequences(new int[]{4, 1, 3, 2, 5}));
        List<String> levelOrder = Arrays.asList("1", "2", "3", "3", "4", "6", "1");
        TreeNode root = solution.buildTree(levelOrder);
        List<List<TreeNode>> ans = solution.allConnectedPath(root, 6 );
        for (List<TreeNode> path : ans) {
            System.out.println(path);
        }

    }

    public static List<String> missingWords(String s, String t) {
        // Write your code here
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        String[] sarray = s.split(" ");
        if (t == null || t.length() == 0) {
            return Arrays.asList(s.split(" "));
        }
        String[] tarray = t.split(" ");
        int pt1 = 0, pt2 = 0;
        while (pt1 < sarray.length && pt2 < tarray.length) {
            if (!sarray[pt1].equals(tarray[pt2])) {
                res.add(sarray[pt1++]);
            } else {
                pt1++;
                pt2++;
            }
        }
        while (pt1 < sarray.length) {
            res.add(sarray[pt1++]);
        }
        return res;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }
}
