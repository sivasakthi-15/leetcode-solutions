import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }

        int LEFT = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);

        int MIDDLE = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);

        int RIGHT = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        int answer = 2 * n;

        for (int mask : map.values()) {

            boolean leftFree = (mask & LEFT) == 0;
            boolean rightFree = (mask & RIGHT) == 0;

            if (leftFree && rightFree) {
                continue;
            }

            answer--;

            if (leftFree || rightFree || (mask & MIDDLE) == 0) {
                continue;
            }

            answer--;
        }

        return answer;
    }
}