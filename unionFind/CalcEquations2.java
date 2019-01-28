package algorithm.unionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CalcEquations2 {
    public static void main(String[] args) {
        CalcEquations2 solution = new CalcEquations2();
        String[][] equations = new String[][] {
                {"a", "b"}, {"c", "d"},{"d", "e"}, {"b", "e"}, {"a", "f"}
        };
        double[] values = new double[] {2.0, 0.5, 0.5, 3.0, 8};
        String[][] queries = new String[][] {
                {"a", "c"}, {"b", "a"}, {"a", "e"}, {"b", "f"}
        };
        System.out.println(Arrays.toString(solution.calcEquation2(
                equations, values,queries
        )));
    }

    public double[] calcEquation2(String[][] equations, double[] values,
                                 String[][] queries) {
        Map<String, Integer> paramToId = new HashMap<>();
        int id = 0;
        for (String[] equ : equations) {
            if(!paramToId.containsKey(equ[0])) {
                paramToId.put(equ[0], id++);
            }
            if(!paramToId.containsKey(equ[1])) {
                paramToId.put(equ[1], id++);
            }
        }

        UnionFind uf = new UnionFind(id);
        for (int i = 0; i < equations.length; i++) {
            uf.union(paramToId.get(equations[i][0]), paramToId.get(equations[i][1]),
                    values[i]);
        }
        double[] result = new double[queries.length];
        for (int i = 0; i < result.length; i++) {
            if (!paramToId.containsKey(queries[i][0]) ||
            !paramToId.containsKey(queries[i][1])) {
                result[i] = -1.0;
                continue;
            }
            result[i] = uf.calc(paramToId.get(queries[i][0]),
                    paramToId.get(queries[i][1]));
        }
        return result;

    }
    class UnionFind{
        private int[] ids;
        private int[] sizes;
        private double[] weights;
        public UnionFind(int size) {
            ids = new int[size];
            sizes = new int[size];
            weights = new double[size];

            for (int i = 0; i < size; i++) {
                ids[i] = i;
                sizes[i] = 1;
                weights[i] = 1;

            }
        }

        private int pureRoot(int a) {
            while(ids[a] != a) {
                a = ids[a];
            }
            return a;
        }
        public void union(int a, int b, double w) {
            int rootA = pureRoot(a);
            int rootB = pureRoot(b);
            if (rootA == rootB) {
                return;
            }
            if (sizes[rootA] >= sizes[rootB]) {
                sizes[rootA] += sizes[rootB];

                double x = weights[a] / w / weights[b];
                for (int i = 0; i < ids.length; i++) {
                    if (pureRoot(i) == rootB ) {
                        weights[i] *= x;
                    }
                }
                for (int i = 0; i < ids.length; i++) {
                    if (pureRoot(i) == rootB) {
                        ids[i] = rootA;
                    }
                }
            } else {
                sizes[rootB] += sizes[rootA];
                double x = weights[b] * w / weights[a];
                for (int i = 0; i < ids.length; i++) {
                    if (pureRoot(i) == rootA ) {
                        weights[i] *= x;
                    }
                }
                for (int i = 0; i < ids.length; i++) {
                    if (pureRoot(i) == rootA) {
                        ids[i] = rootB;
                    }
                }

            }
            System.out.println("inside union");
            System.out.println(Arrays.toString(ids));
            System.out.println(Arrays.toString(weights));
        }
        public double calc(int a, int b) {
            int rootA = pureRoot(a);
            int rootB = pureRoot(b);
            System.out.println("inside calc");
            System.out.println(Arrays.toString(ids));
            System.out.println(Arrays.toString(weights));
            if (rootA != rootB) {
                return -1.0;
            }
            return weights[a] / weights[b];
        }
    }
}
