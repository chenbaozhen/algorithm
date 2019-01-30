package algorithm.ExpressionTree;

import algorithm.myUtility.Display;

import java.util.ArrayDeque;
import java.util.Deque;

public class TenaryExpressionTree {
    public static void main(String[] args) {
        TenaryExpressionTree solution = new TenaryExpressionTree();
        String ex = "T?T?F:5:3";
        char c = solution.parseTenary(ex);
        Display.myPrint(c);
    }
    public TreeNode tenaryToTree(String ex) {
        // a?b?c:d:e
        Deque<TreeNode> deque = new ArrayDeque<>();

       for (int i = ex.length() - 1; i >= 0; i++) {
           char c = ex.charAt(i);
           TreeNode root = new TreeNode(c);
           if (c == ':') {
               continue;
           } else if (!deque.isEmpty() && deque.peek().c == '?') {
               deque.pop();
               root.left = deque.pop();
               root.right = deque.pop();
           }
           deque.push(root);
       }

        return deque.pop();
    }
    public char parseTenary(String ex) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = ex.length() - 1; i >= 0; i--) {
            char c  = ex.charAt(i);
            if (c == ':') continue;
            if (!deque.isEmpty() && deque.peek() == '?') {
                deque.pop();
                char c1 = deque.pop();
                char c2 = deque.pop();
                deque.push(c == 'T' ? c1 : c2);
            } else {
                deque.push(c);
            }
        }

        return deque.pop();
    }
}
