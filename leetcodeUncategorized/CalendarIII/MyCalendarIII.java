package algorithm.leetcodeUncategorized.CalendarIII;

public class MyCalendarIII {

    public static void main(String[] args) {
        MyCalendarThree solution = new MyCalendarThree();
        solution.book(10, 20);
        solution.book(25, 30);
        solution.book(15,22);
    }
}


class MyCalendarThree {
    SegmentTree segmentTree;
    public class SegmentTree {
        TreeNode root;
        public class TreeNode {
            TreeNode left;
            TreeNode right;
            int leftBound;
            int rightBound;
            int lazy;
            int max;
            public TreeNode(int l,int r) {
                leftBound = l;
                rightBound = r;
            }
            public int getMid(){
                return leftBound + (rightBound-leftBound)/2;
            }
            public TreeNode getLeft() {
                if (left == null) left = new TreeNode(leftBound,getMid());
                return left;
            }
            public TreeNode getRight() {
                if (right == null) right = new TreeNode(getMid()+1,rightBound);
                return right;
            }
        }
        public SegmentTree() {
            root = new TreeNode(0,Integer.MAX_VALUE);
        }
        public TreeNode update(int l,int r,int val) {
            return update(root,l,r,val);
        }
        public TreeNode update(TreeNode node,int l,int r,int val) {
            if (node.lazy != 0) {
                node.max += node.lazy;
                if (node.leftBound != node.rightBound) {
                    node.getLeft().lazy += node.lazy;
                    node.getRight().lazy += node.lazy;
                }
                node.lazy = 0;
            }
            if(node.rightBound < node.leftBound || r < node.leftBound || l > node.rightBound) return node;
            if(node.leftBound >= l && node.rightBound <= r ) {
                node.max += val;
                if (node.leftBound != node.rightBound) {
                    node.getLeft().lazy += val;
                    node.getRight().lazy += val;
                }
                return node;
            }
            node.left = update(node.getLeft(),l,r,val);
            node.right = update(node.getRight(),l,r,val);
            node.max = Math.max(node.left.max,node.right.max);;
            return node;
        }
        public int query(int l,int r) {
            return query(root,l,r);
        }
        public  int query(TreeNode node,int l,int r) {
            if(node.rightBound < node.leftBound || r < node.leftBound || l > node.rightBound) return 0;
            if (node.lazy != 0) {
                node.max += node.lazy;
                if (node.leftBound != node.rightBound) {
                    node.getLeft().lazy += node.lazy;
                    node.getRight().lazy += node.lazy;
                }
                node.lazy = 0;
            }
            if(node.leftBound >= l && node.rightBound <= r ) return node.max;
            return Math.max(query(node.getLeft(),l,r),query(node.getRight(),l,r)) ;
        }
    }
    public MyCalendarThree() {
        segmentTree = new SegmentTree();
    }

    public int book(int start, int end) {
        segmentTree.update(start,end-1,1);
        return segmentTree.query(0,Integer.MAX_VALUE);
    }
}