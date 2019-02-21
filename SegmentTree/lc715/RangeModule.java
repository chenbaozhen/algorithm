package algorithm.SegmentTree.lc715;

public class RangeModule {
    public static void main(String[] args) {
        RangeModule rm = new RangeModule();
        rm.addRange(100, 200);
        rm.addRange(400, 500);
    }

    SegmentTreeNode root;
    private SegmentTreeNode addRange(SegmentTreeNode node, int start, int end) {
        if (node == null) return null;
        if (node.isCovered || (start <= node.start && end >= node.end)) {
            node.isCovered = true;
            addRange(node.leftChild, start, end);
            addRange(node.rightChild, start, end);
            return node;
        }
        if (!node.isLeaf)  {
            addRange(node.leftChild, start, end);
            addRange(node.rightChild, start,end);
        } else {
            if (start > node.end || end < node.start ) {
                return node;
            } else if (start <= node.start && end >= node.end) {
                node.isCovered = true;
                return node;
            } else if (start <= node.start) {
                node.isLeaf = false;
                node.leftChild = new SegmentTreeNode(node.start, end, node.start);
                node.leftChild.isCovered = true;
                node.rightChild = new SegmentTreeNode(end, node.end, end);
                if (node.leftChild.start == node.leftChild.end) return null;
                if (node.rightChild.start == node.rightChild.end) return null;
                node.mid = end;
            } else if (end >= node.end) {
                node.isLeaf = false;
                node.leftChild = new SegmentTreeNode(node.start, start, node.start);
                node.rightChild = new SegmentTreeNode(start, node.end, start);
                node.rightChild.isCovered = true;
                if (node.leftChild.start == node.leftChild.end) return null;
                if (node.rightChild.start == node.rightChild.end) return null;

                node.mid = start;
            } else {
                node.isLeaf = false;
                node.leftChild = new SegmentTreeNode(node.start, start + (end - start) / 2, start);
                node.rightChild = new SegmentTreeNode(start + (end - start) / 2, node.end, end);
                if (node.leftChild.start == node.leftChild.end) return null;
                if (node.rightChild.start == node.rightChild.end) return null;
                addRange(node.leftChild, start, end);
                addRange(node.rightChild. start,end);
            }

        }

        return node;
    }

    public RangeModule() {
        root = new SegmentTreeNode(0, 1000000000, 500000000);
        root.isLeaf = true;
    }

    public void addRange(int left, int right) {
        addRange(root, left, right);
    }

    public boolean queryRange(int left, int right) {
        return false;
    }

    public void removeRange(int left, int right) {

    }


}
class SegmentTreeNode {
    int start, end, mid;
    SegmentTreeNode leftChild, rightChild;
    boolean isCovered;
    boolean isLeaf;
    public SegmentTreeNode(int start, int end, int mid) {
        this.start = start;
        this.end = end;
        this.mid = mid;
        isLeaf = true;
        isCovered = false;
    }
}