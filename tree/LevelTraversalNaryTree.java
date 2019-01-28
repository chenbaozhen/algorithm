package algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelTraversalNaryTree {
    //Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

    //For example, given a 3-ary tree:
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>>  result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            result.add(list);
            for (int i = 0; i < size; i++)  {
                Node node = queue.poll();
                list.add(node.val);
                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
        }
        return result;

    }
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
