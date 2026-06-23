class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        // Length = 2
        for (int x = 1; x <= m; x++) {
            up[x] = x - 1;
            down[x] = m - x;
        }

        // Build lengths 3..n
        for (int len = 3; len <= n; len++) {

            long[] prefixDown = new long[m + 1];
            for (int i = 1; i <= m; i++) {
                prefixDown[i] = (prefixDown[i - 1] + down[i]) % MOD;
            }

            long[] suffixUp = new long[m + 2];
            for (int i = m; i >= 1; i--) {
                suffixUp[i] = (suffixUp[i + 1] + up[i]) % MOD;
            }

            long[] newUp = new long[m + 1];
            long[] newDown = new long[m + 1];

            for (int x = 1; x <= m; x++) {
                newUp[x] = prefixDown[x - 1];
                newDown[x] = suffixUp[x + 1];
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        if (n == 2) {
            for (int x = 1; x <= m; x++) {
                ans = (ans + up[x] + down[x]) % MOD;
            }
        } else {
            for (int x = 1; x <= m; x++) {
                ans = (ans + up[x] + down[x]) % MOD;
            }
        }

        return (int) ans;
    }
}