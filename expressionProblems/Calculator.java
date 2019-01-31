package algorithm.expressionProblems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    Map<Character, Integer> priorityMap = new HashMap<>();
    public int calculate1(String s) {
        // + -, (), no negative, no * /
        // stack solution
        if (s == null || s.length() == 0) return 0;

//        String operators = "()+-*/^";
//        int[] priorities = new int[]{0, 0, 1, 1, 2, 2, 3};
//        for (int i = 0; i < operators.length(); i++) {
//            priorityMap.put(operators.charAt(i), priorities[i]);
//        }
        Deque<Integer> numDeque = new ArrayDeque<>();
        Deque<Character> opDeque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (isNumber(s.charAt(i))) {
                int pt = i;
                while (pt + 1 < s.length() && isNumber(s.charAt(pt + 1))) pt++;
                numDeque.push(Integer.parseInt(s.substring(i, pt + 1)));
                i = pt;
            } else if (s.charAt(i) == ' '){
                ;
            } else if (s.charAt(i) == '('){
                opDeque.push('(');
            } else if (s.charAt(i) == ')' || s.charAt(i) == '+' || s.charAt(i) == '-') {
                while (!opDeque.isEmpty() && opDeque.peek() != '(') {
                    char op = opDeque.pop();
                    numDeque.push(operation(op, numDeque.pop(), numDeque.pop()));
                }
                if (s.charAt(i) == ')') opDeque.pop();
                if (s.charAt(i) != ')') opDeque.push(s.charAt(i));
            }

        }
        while (!opDeque.isEmpty() && opDeque.peek() != ')') {
            char op = opDeque.pop();
            numDeque.push(operation(op, numDeque.pop(), numDeque.pop()));
        }

        return numDeque.peek();
    }
    public int calculate2(String s) {
      // non stack solution
        int sum = 0;
        char sign = '+';
        int prod = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') continue;
            if (isNumber(s.charAt(i))) {
                int pt = i;
                while (pt + 1 < s.length() && isNumber(s.charAt(pt + 1))) {
                    pt++;
                }
                int n = Integer.parseInt(s.substring(i, pt + 1));
                if (sign == '+') {
                    prod = n;
                } else if (sign == '-') {
                    prod = -1 * n;
                } else if (sign == '*') {
                    prod *= n;
                } else if (sign == '/') {
                    prod /= n;
                }
                i = pt;
            } else {
                char op = s.charAt(i);
                if (op == '+' || op == '-') {
                    sum += prod;
                    prod = 1;
                }
                sign = op;
            }
        }
        sum += prod;
        return sum;
    }

    public int calculate2b(String s) {
        int result = 0, prev = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (isNumber(c)) {
                int n = c - '0';
                while (i + 1 < s.length() && isNumber(s.charAt(i + 1))) {
                    n *= 10;
                    n += s.charAt(++i) - '0';
                }
                if (sign == '+') {
                    result += n;
                    prev = n;
                } else if (sign == '-') {
                    result -= n;
                    prev = -1 * n;
                } else if (sign == '*') {
                    result += -1 * prev + prev * n;
                    prev = prev * n;
                } else if (sign == '/') {
                    result += -1 * prev + prev / n;
                    prev = prev / n;
                }
            } else {
                sign = c;
            }
        }
        return result;
    }
    public int calculate2c(String s) {
        Deque<Integer> deque = new ArrayDeque<>();
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (c <= '9' && c >= '0') {
                int n = c - '0';
                while (i + 1 < s.length() && s.charAt(i + 1) <= '9' && s.charAt(i + 1) >= '0') {
                    n *= 10;
                    n += s.charAt(++i) - '0';
                }
                if (sign == '+') {
                    deque.push(n);
                } else if (sign == '-') {
                    deque.push(-1 * n);
                } else if (sign == '*') {
                    deque.push(deque.pop() * n);
                } else if (sign == '/') {
                    deque.push(deque.pop() / n);
                }
            } else {
                sign = c;
            }
        }
        int sum = 0;
        while (!deque.isEmpty()) {
            sum += deque.pop();
        }
        return sum;
    }
    private int operation(char op, int num2, int num1) {
        return num1 + (op == '+' ? num2 : -1 * num2);
    }
    private boolean isNumber(char c) {
        return c <= '9' && c >= '0';
    }
}
