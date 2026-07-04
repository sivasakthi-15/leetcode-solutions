import java.util.*;

class Solution {

    int answer = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {

        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(new int[]{road[1], road[2]});
            graph[road[1]].add(new int[]{road[0], road[2]});
        }

        boolean[] visited = new boolean[n + 1];

        dfs(1, graph, visited);

        return answer;
    }

    private void dfs(int node, List<int[]>[] graph, boolean[] visited) {

        visited[node] = true;

        for (int[] edge : graph[node]) {

            answer = Math.min(answer, edge[1]);

            if (!visited[edge[0]]) {
                dfs(edge[0], graph, visited);
            }
        }
    }
}