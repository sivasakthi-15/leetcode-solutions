class Solution {

    class Node {
        char leftChar, rightChar;
        int prefix, suffix, best, length;

        Node() {}

        Node(char c) {
            leftChar = rightChar = c;
            prefix = suffix = best = length = 1;
        }
    }

    Node[] tree;
    String s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        this.s = s;

        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] answer = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, ch);

            answer[i] = tree[1].best;
        }

        return answer;
    }

    // Build segment tree
    private void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = new Node(s.charAt(left));
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two nodes
    private Node merge(Node a, Node b) {

        Node c = new Node();

        c.length = a.length + b.length;

        c.leftChar = a.leftChar;
        c.rightChar = b.rightChar;

        // Initially, prefix and suffix come from respective sides
        c.prefix = a.prefix;
        c.suffix = b.suffix;

        if (a.rightChar == b.leftChar) {

            // Entire left part has the same character
            if (a.prefix == a.length) {
                c.prefix = a.length + b.prefix;
            }

            // Entire right part has the same character
            if (b.suffix == b.length) {
                c.suffix = b.length + a.suffix;
            }

            // A repeating sequence can cross the boundary
            c.best = Math.max(
                    Math.max(a.best, b.best),
                    a.suffix + b.prefix
            );

        } else {

            // No connection across the boundary
            c.best = Math.max(a.best, b.best);
        }

        return c;
    }

    // Point update
    private void update(
            int node,
            int left,
            int right,
            int index,
            char ch) {

        if (left == right) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        // Recalculate current node
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
}