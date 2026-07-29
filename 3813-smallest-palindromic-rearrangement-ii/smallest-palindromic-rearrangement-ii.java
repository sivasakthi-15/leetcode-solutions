class Solution {

    static final long LIMIT = 1000001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] half = new int[26];
        String mid = "";

        int len = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i];

            if ((freq[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
        }

        long total = countWays(half, len);

        if (total < k)
            return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {

            for (int ch = 0; ch < 26; ch++) {

                if (half[ch] == 0)
                    continue;

                half[ch]--;

                long ways = countWays(half, len - pos - 1);

                if (ways >= k) {
                    left.append((char) ('a' + ch));
                    break;
                } else {
                    k -= ways;
                    half[ch]++;
                }
            }
        }

        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString() + mid + right.toString();
    }

    private long countWays(int[] cnt, int total) {

        long ans = 1;

        int remain = total;

        for (int i = 0; i < 26; i++) {

            int f = cnt[i];

            if (f == 0)
                continue;

            ans *= comb(remain, f);

            if (ans > LIMIT)
                ans = LIMIT;

            remain -= f;
        }

        return ans;
    }

    private long comb(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long ans = 1;

        for (int i = 1; i <= r; i++) {

            ans = ans * (n - r + i) / i;

            if (ans > LIMIT)
                return LIMIT;
        }

        return ans;
    }
}