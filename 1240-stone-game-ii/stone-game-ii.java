class Solution {
    int n;
    int[][] memo;
    int[] prefix;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        // Prefix sum
        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + piles[i];
        }

        // memo[i][M] = maximum stones current player can get
        // starting from index i with current M
        memo = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }

        return solve(0, 1);
    }

    private int solve(int i, int M) {
        // No piles left
        if (i == n) {
            return 0;
        }

        // Already calculated
        if (memo[i][M] != -1) {
            return memo[i][M];
        }

        // Total stones remaining
        int remaining = prefix[n] - prefix[i];

        int best = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            int newM = Math.max(M, X);

            // Opponent's maximum possible stones
            int opponent = solve(i + X, newM);

            // Whatever opponent gets, we get the rest
            int current = remaining - opponent;

            best = Math.max(best, current);
        }

        memo[i][M] = best;

        return best;
    }
}