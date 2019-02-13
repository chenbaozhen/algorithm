package algorithm.SegmentTree;

import java.util.*;

public class LC699 {

    public static void main(String[] args) {
        Solution699 solution = new Solution699();
        solution.fallingSquares(new int[][]{ {1,2},{2,3},{6,1}});
    }


}


class Solution699 {
    public List<Integer> fallingSquares(int[][] positions) {
        Set<Integer> set = new HashSet<>();
        for (int[] pos : positions) {
            set.add(pos[0]);
            set.add(pos[0] + pos[1] - 1);
        }
        List<Integer> xList = new ArrayList<>(set);
        Collections.sort(xList);
        Map<Integer, Integer> idMap = new HashMap<>();
        for (int i = 0; i < xList.size(); i++) {
            idMap.put(xList.get(i), i);
        }
        SegmentTreeNode root = buildTree(0, xList.size() - 1, 0);
        List<Integer> ans = new LinkedList<>();
        for (int[] pos: positions) {
            int segHeight = queryHeight(root, idMap.get(pos[0]), idMap.get(pos[0] + pos[1] - 1));
            update(root,  idMap.get(pos[0]), idMap.get(pos[0] + pos[1] - 1), segHeight + pos[1]);
            ans.add(root.height);
        }
        return ans;
    }
    private void update(SegmentTreeNode node, int start, int end, int height) {
        if (node == null || start > node.end || end < node.start) return;
        node.height = Math.max(node.height, height);
        update(node.left, start, end, height);
        update(node.right, start, end, height);

    }
    private int queryHeight(SegmentTreeNode node, int start, int end) {
        if (start > end) return 0;
        if (node == null || start > node.end || end < node.start) return 0;
        if (start <= node.start && end >= node.end) return node.height;
        return Math.max(queryHeight(node.left, start, end),
                queryHeight(node.right, start, end));
    }
    private SegmentTreeNode buildTree(int start, int end, int height) {
        if (start > end) return null;
        SegmentTreeNode node = new SegmentTreeNode(start, end, height);
        if (start == end) return node;
        int mid = start + (end - start) / 2;
        node.left = buildTree(start, mid, height);
        node.right = buildTree(mid + 1, end, height);
        return node;
    }

    class SegmentTreeNode {
        int start, end, height;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }
}
