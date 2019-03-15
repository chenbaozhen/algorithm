package algorithm.leetcodeUncategorized.Calendar3;

public class MyCalendarThree {
    BSTree tree;
    public MyCalendarThree() {
        //intialize the BSTree
        tree = new BSTree();
    }

    public int book(int start, int end) {
        //delegate all the request to the BSTree
        tree.insert(start, end);
        return tree.query();
    }
    public static void main(String[] args) {
        MyCalendarThree solution = new MyCalendarThree();
        solution.book(47,50);
        solution.book(1,10);
        solution.book(27,36);
        solution.book(40,47);
        solution.book(20,27);
        solution.book(15,23);
        solution.book(10,18);
        solution.book(27,36);
        solution.book(17,25);
        solution.book(100,180);
        //solution.book([47,50],[1,10],[27,36],[40,47],[20,27],[15,23]) [10,18],[27,36],[17,25],
    }
}


class BSTree {
    TreeNode root;
    int max;
    //constructor
    public BSTree() {
        root = null;
        max = 0;
    }
    //update
    public TreeNode insert(int s, int e) {
        root = insert(root, s, e, 1);
        return root;
    }
    public TreeNode insert(TreeNode node, int s, int e, int repeat) {
        if (s >= e) return null;
        if (node == null) {
            max = Math.max(repeat, max);
            return new TreeNode(s, e, repeat);
        }
        //no overlap
        if (e <= node.start) {
            node.left = insert(node.left, s, e, repeat);
            max = Math.max(max, root.repeat);
            return node;
        } else if (s >= node.end) {
            node.right = insert(node.right, s, e, repeat);
            max = Math.max(max, root.repeat);
            return node;
        }

        //overlap
        int a = Math.min(s, node.start);
        int b = Math.max(s, node.start);
        int c = Math.min(e, node.end);
        int d = Math.max(e, node.end);

        node.left = insert(node.left, a, b, s < node.start ? repeat : node.repeat);
        node.right = insert(node.right, c, d, e > node.end ? repeat : node.repeat);
        node.start = b;
        node.end = c;
        node.repeat += repeat;
        max = Math.max(node.repeat, max);
        return node;
    }

    // query
    public int query() {
        return max;
    }

}
class TreeNode {
    int start, end, repeat;
    TreeNode left, right;
    public TreeNode(int start, int end) {
        this.start = start;
        this.end = end;
    }
    public TreeNode(int start, int end, int repeat) {
        this.start = start;
        this.end = end;
        this.repeat = repeat;
    }
}
