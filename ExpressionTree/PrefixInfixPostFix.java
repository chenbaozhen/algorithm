package algorithm.ExpressionTree;

import algorithm.unionFind.TreeUnionFind;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import myUtility.Display;

import java.util.*;

public class PrefixInfixPostFix {
    //Convert between prefix, infix, postfixes
    public static void main(String[] args) {
        PrefixInfixPostFix solution = new PrefixInfixPostFix();
        String ex = "1 + (2 + 3) * 4  + 5";
        String postFix = solution.infixToPostfix(ex);
        Display.myPrint(postFix);
        String infix = solution.infixToPrefix(ex);
        Display.myPrint(infix);
        String prefix2 = solution.postfixToPrefix(postFix);
        Display.myPrint(prefix2);
        String infix2 = solution.postfixToInfix(postFix);
        Display.myPrint(infix2);
        String infix3 = solution.prefixToInfix(prefix2);
        Display.myPrint(infix3);
        String postFix2 = solution.prefixToPostfix(prefix2);
        Display.myPrint(postFix2);
        solution.postfixToTreeNode(postFix);
    }
    Map<Character, Integer> priorityMap = new HashMap<>();
    public String infixToPostfix(String ex) {
        // 1 + (2 + 3) * 4 + 5
        String operators = "()+-*/^";
        int[] priorities = new int[]{0, 0, 1, 1, 2, 2, 3};
        for (int i = 0; i < operators.length(); i++) {
            priorityMap.put(operators.charAt(i), priorities[i]);
        }
        StringBuilder sb = new StringBuilder();
        // number, add
        // find all operator which has higher prority or smilar
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < ex.length(); i++) {
            char c = ex.charAt(i);
            if (c <= '9' && c >= '0') {
                sb.append(c);
            } else if (c == ' ') {
                continue;
            } else if (c == '(') {
                deque.push('(');
            }
            else  if (c == ')') {
                while (!deque.isEmpty() && deque.peek() != '(') {
                    sb.append(deque.pop());
                }
                deque.pop();
            } else {
                while (!deque.isEmpty() && priorityMap.get(deque.peek()) >= priorityMap.get(c)) {
                    sb.append(deque.pop());
                }
                deque.push(c);
            }
        }
        while (!deque.isEmpty()) {
            sb.append(deque.pop());
        }
        return sb.toString();
    }
    public String postfixToInfix(String ex) {
        //"123+4*+5+"  to
        // 1 + (2 + 3) * 4 + 5
        //
        Deque<String> deque = new LinkedList<>();
        Deque<Integer> pDeque = new LinkedList<>();
        for (int i = 0; i < ex.length(); i++) {
            char c = ex.charAt(i);
            if ("+-*/^".indexOf(c) < 0 ) {
                deque.push(Character.toString(c));
                pDeque.push(6);
            } else {
                String operand2 = deque.pop();
                String operand1 = deque.pop();
                int operand2pr = pDeque.pop();
                int operand1pr = pDeque.pop();
                int prC = priorityMap.get(c);
                String tmp = "";
                if (operand1pr < prC) {
                    tmp += "(" + operand1 + ")" + Character.toString(c);
                } else {
                    tmp += operand1 + Character.toString(c);
                }
                if (operand2pr < prC) {
                    tmp += "(" + operand2 + ")";
                } else tmp += operand2;
                pDeque.push(prC);
                deque.push(tmp);
            }
        }
        return deque.pop();
    }
    public String infixToPrefix(String ex) {
        // 1 + (2 + 3) * 4 + 5

        // number, ++1*+2345
        // 1 + 2 = > +12
        // 5 4 3 2 + * 1 ++
        // + +
        String operators = "()+-*/^";
        int[] priorities = new int[]{0, 0, 1, 1, 2, 2, 3};
        for (int i = 0; i < operators.length(); i++) {
            priorityMap.put(operators.charAt(i), priorities[i]);
        }
        StringBuilder sb = new StringBuilder();
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = ex.length() - 1; i >= 0; i--) {
            char c = ex.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            } else if (c == ')') {
                deque.push(c);

            } else if (c == '(') {
                while (!deque.isEmpty() && deque.peek() != ')') {
                    sb.append(deque.pop());
                }
                deque.pop();
            } else if (c == ' ') {
                continue;
            } else {
                while (!deque.isEmpty() && priorityMap.get(deque.peek()) >= priorityMap.get(c)) {
                    sb.append(deque.pop());
                }
                deque.push(c);
            }
        }
        while (!deque.isEmpty()) {
            sb.append(deque.pop());
        }

        return sb.reverse().toString();
    }
    public String postfixToPrefix(String ex) {
        StringBuilder sb = new StringBuilder();
        // +5+*4+321  ++      5
        Deque<String> deque = new LinkedList<>();
        for (int i = 0; i < ex.length(); i++) {
            char c = ex.charAt(i);
            if ("+-*/".indexOf(c) < 0) {
                deque.push(Character.toString(c));
            } else {
                String s2 = deque.pop();
                String s1 = deque.pop();
                deque.push(Character.toString(c) + s1 + s2 );
            }
        }
        return deque.pop();
    }
    public String prefixToInfix(String ex){
        Deque<String> deque = new ArrayDeque<>();
        Deque<Integer> priorities = new ArrayDeque<>();
        for (int i = ex.length() - 1; i >= 0; i--) {
            char c = ex.charAt(i);
            if ("-+*/^".indexOf(c) < 0) {
                deque.push(Character.toString(c));
                priorities.push(6);
            } else {
                String op1 = deque.pop();
                String op2 = deque.pop();
                int op1pr = priorities.pop();
                int op2pr = priorities.pop();
                String tmp = "";
                int pr = priorityMap.get(c);
                if (op1pr < pr) {
                    tmp += "(" + op1 + ")" + Character.toString(c);
                } else {
                    tmp += op1 + Character.toString(c);
                }
                if (op2pr < pr) {
                    tmp += "(" + op2 + ")";
                } else {
                    tmp += op2;
                }
                deque.push(tmp);
                priorities.push(pr);
            }
        }

        return deque.pop();
    }
    public String prefixToPostfix(String ex) {
        Deque<String> deque = new ArrayDeque<>();
        for (int i = ex.length() - 1; i >= 0; i--) {
            char c = ex.charAt(i);
            if ("+-*/^".indexOf(c) < 0) {
                deque.push(Character.toString(c));
            } else {
                String op1 = deque.pop();
                String op2 = deque.pop();
                deque.push(op1 + op2 + Character.toString(c));
            }
        }
        return deque.pop();
    }
    public TreeNode infixToTreeNode(){

        return null;
    }
    public TreeNode postfixToTreeNode(String ex){
        Deque<TreeNode> deque = new ArrayDeque<>();
        for (int i = 0; i < ex.length(); i++) {
            char c = ex.charAt(i);
            TreeNode node = new TreeNode(c);
            if ("+-*/^".indexOf(c) >= 0) {
                node.right = deque.pop();
                node.left = deque.pop();
            }
            deque.push(node);
        }
        return deque.pop();
    }
    public TreeNode prefixToTreeNode(String ex){
        Deque<TreeNode> deque = new ArrayDeque<>();
        for (int i = ex.length() - 1; i >= 0; i++) {
            char c = ex.charAt(i);
            TreeNode node = new TreeNode(c);
            if ("*-+/^".indexOf(c) >= 0 ) {
                node.left = deque.pop();
                node.right = deque.pop();
            }
            deque.push(node);
        }
        return deque.pop();
    }
    public TreeNode infixToTreeNode(String ex) {
        Deque<TreeNode> dequeNode = new ArrayDeque<>();
        Deque<Character> dequeOperator = new ArrayDeque<>();
        for (int i = 0; i  < ex.length(); i ++) {
            char c = ex.charAt(i);
            if (c == ' ') continue;
            if ("()+-*/^".indexOf(c) < 0) {
                dequeNode.push(new TreeNode(c));
            } else if (c == '('){
                dequeOperator.push(c);
            } else if (c == ')') {
                while (!dequeOperator.isEmpty() && dequeOperator.peek() != '(') {
                    TreeNode node = new TreeNode(dequeOperator.pop());
                    node.right = dequeNode.pop();
                    node.left = dequeNode.pop();
                    dequeNode.push(node);
                }
                dequeOperator.pop();
            } else {
                while(dequeOperator.size() > 0 &&
                        priorityMap.get(dequeOperator.peek()) >= priorityMap.get(c)) {
                    TreeNode node = new TreeNode(c);
                    node.right = dequeNode.pop();
                    node.left = dequeNode.pop();
                    dequeNode.push(node);
                }
            }
        }
        return dequeNode.pop();
    }
}
class TreeNode{
    char c;
    TreeNode left;
    TreeNode right;

    public TreeNode(char c) {
        this.c = c;
    }
}
