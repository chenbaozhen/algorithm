package algorithm.miscs.bloomFilter;

import java.util.*;

public class BuildGraph {

    public List<Node> buildGraph(List<Span> spans) {
        // Build all the graph node
        List<Node> graph = new ArrayList<>();
        for (Span span : spans) {
            graph.add(new Node(span));
        }
        Collections.sort(spans, new Comparator<Span>() {
            @Override
            public int compare(Span o1, Span o2) {
                return o1.start - o2.start;
            }
        });

        for (int i = 0; i < graph.size() - 1; i++) {
            Node node = graph.get(i);
            // binary search to find the first non overlapping node
            int pointer = firstNonOverlap(graph, i);

            // starting from pointer, linear scan with early termination;
            // how many points we need to do depends on the number of edges;
            int curEnd = Integer.MAX_VALUE;
            while (pointer < graph.size()) {
                Node curNode = graph.get(pointer);
                if (curNode.span.start >= curEnd) break; // early termination
                else {
                    node.connectTo(curNode);
                    curEnd = Math.min(curEnd, curNode.span.end); // update the min ending position.
                }
                pointer++;
            }
        }
        return graph;
    }
    // binary search to find the first Node that non overlap with nodes at index i;
    private int firstNonOverlap(List<Node> graph, int i) {
        int end = graph.get(i).span.end;
        int l = i + 1, r = graph.size() - 1;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (graph.get(m).span.start >= end) r = m;
            else l = m + 1;
        }
        if (graph.get(r).span.start < end) {
            return r + 1;
        } else return r;

    }

    class Node {
        Span span;
        Set<Node> children;

        void connectTo(Node that) {
            this.children.add(that);
        }

    public Node(Span span) {
        this.span = span;
        children = new HashSet<>();
    }
}
    class Span {
        String label;
        int start;
        int end;

        public Span(String label, int start, int end) {
            this.label = label;
            this.start = start;
            this.end = end;
        }
    }
}


/*
Your previous Plain Text content is preserved below:

Utterance (sentence):

        .........E...........

...A...   ...B... ...C....... ..D....

book me a dentist appointment for 2pm
0    1  2 3       4           5   6    7

Spans:

A [0,2)
B [3,4)
C [4,5)
D [5,7)
E [2,5)

1. sort by starting time
2. List<Session>:
A(0,2) , E(2,5), B(3,4), C(4,5), D(5,7);

A(0, 2) -> B





A -> B
A -> C
A -> D
A -> E
B -> C
B -> D
B -> E
C -> D
E -> D
...
o(n^3)






Build a graph:
A -> E -----\
 \-> B -> C -> D

In this graph, edges X -> Y satisfy these conditions:
1. X,Y are non-overlapping
    good: A -> E, A -> B
    bad: E -> C
2. Y is on the right of X
    good: A -> B
    bad: B -> A
3. no edge if there is a Z that can be "squeezed" in between X & Y
    good: A -> B
    bad: A -> C (because I can put B in between A and C)

class Span // don't need to implement
  string Label
  int Start
  int End

class Node // don't need to implement
  object Payload // will point to Span
  void ConnectTo(Node other)
  List<Node> nextChildren;

Node nodeA = new Node(spanA)
Node nodeB = new Node(spanB)
nodeA.ConnectTo(nodeB)

input: List<Span>
output: List<Node>, where all nodes are connected according to 1, 2, 3

---




}
*/