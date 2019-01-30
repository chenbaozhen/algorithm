package algorithm.dp.Dec122018;

import algorithm.myUtility.Display;

public class Matches {

    public int numberOfSquares(Node[][] matrix) {
        // preprocessing:
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                Node node = matrix[r][c];
                if (r != 0 && node.hasUp) {
                    node.longestUp = matrix[r - 1][c].longestUp + 1;
                }
                if (c != 0 && node.hasLeft) {
                    node.longestLeft = matrix[r][c - 1].longestLeft + 1;
                }
            }
        }
        int count = 0;
        for (int r = 1; r < matrix.length; r++) {
            for (int c = 1; c < matrix[0].length; c++) {
                Node node = matrix[r][c];
                if (!node.hasLeft || !node.hasUp) {
                    continue;
                }
                int len = Math.min(node.longestLeft, node.longestUp);
                for (int i = 1; i <= len; i++) {
                    Node left = matrix[r][c - i];
                    Node top = matrix[r - i][c];
                    if (left.longestUp >= i && top.longestLeft >= i) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int largestSquares(Node[][] matrix) {
        // preprocessing:
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                Node node = matrix[r][c];
                if (r != 0 && node.hasUp) {
                    node.longestUp = matrix[r - 1][c].longestUp + 1;
                }
                if (c != 0 && node.hasLeft) {
                    node.longestLeft = matrix[r][c - 1].longestLeft + 1;
                }
            }
        }
        int largest = 0;
        for (int r = 1; r < matrix.length; r++) {
            for (int c = 1; c < matrix[0].length; c++) {
                Node node = matrix[r][c];
                if (!node.hasLeft || !node.hasUp) {
                    continue;
                }
                int len = Math.min(node.longestLeft, node.longestUp);
                for (int i = 1; i <= len; i++) {
                    Node left = matrix[r][c - i];
                    Node top = matrix[r - i][c];
                    if (left.longestUp >= i && top.longestLeft >= i) {
                        largest = Math.max(largest, i * i);
                    }
                }
            }
        }
        return largest;
    }
    public static void main(String[] args) {
        Node[][] mt = new Node[5][5];
        mt[0] = new Node[]{new Node(false, false), new Node(false, true),
                new Node(false, true), new Node(false, true), new Node(false, true)};
        mt[1] = new Node[]{new Node(true, false), new Node(false, true),
                new Node(true, true), new Node(true, true), new Node(true, true)};
        mt[2] = new Node[]{new Node(true, false), new Node(true, true),
                new Node(true, true), new Node(true, true), new Node(true, true)};

        mt[3] = new Node[]{new Node(true, false), new Node(true, false),
                new Node(true, true), new Node(true, true), new Node(true, false)};
        mt[4] = new Node[]{new Node(true, true), new Node(false, true),
                new Node(true, true), new Node(true, true), new Node(true, true)};

        Matches solution = new Matches();
        Display.myPrint(solution.numberOfSquares(mt));
        Display.myPrint(solution.largestSquares(mt));
    }

}

class Node {
    boolean hasUp;
    boolean hasLeft;
    int longestUp;
    int longestLeft;
    public Node(boolean hasUp, boolean hasLeft) {
        this.hasUp = hasUp;
        this.hasLeft = hasLeft;
        longestLeft = 0;
        longestUp = 0;
    }
}