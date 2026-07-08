class Solution {
    private static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        // prefNZ[i] = number of non-zero digits in s[0..i-1]
        int[] prefNZ = new int[n + 1];

        // prefSum[i] = sum of digits in s[0..i-1]
        int[] prefSum = new int[n + 1];

        int nonZeroCount = 0;

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            prefNZ[i + 1] = prefNZ[i];
            prefSum[i + 1] = prefSum[i] + digit;

            if (digit != 0) {
                prefNZ[i + 1]++;
                nonZeroCount++;
            }
        }

        // powers of 10 modulo MOD
        long[] pow10 = new long[nonZeroCount + 1];
        pow10[0] = 1;
        for (int i = 1; i <= nonZeroCount; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // prefix hash of non-zero digits
        long[] hash = new long[nonZeroCount + 1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                idx++;
                hash[idx] = (hash[idx - 1] * 10 + digit) % MOD;
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = prefNZ[l];
            int right = prefNZ[r + 1];
            int len = right - left;

            if (len == 0) {
                ans[i] = 0;
                continue;
            }

            long x = (hash[right] - (hash[left] * pow10[len]) % MOD + MOD) % MOD;
            long sum = prefSum[r + 1] - prefSum[l];

            ans[i] = (int) ((x * sum) % MOD);
        }

        return ans; // return answer
    }
}