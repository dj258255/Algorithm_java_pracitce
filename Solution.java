import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for (int T = 1; T <= t; T++) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            int[] times = new int[n];

            for (int i = 0; i < n; i++) {
                times[i] = scan.nextInt();
            }

            Arrays.sort(times);

            System.out.println("#" + T + " " + BinarySearch(times, m));
        }
    }

    public static long BinarySearch(int[] times, int m) {
        long left = 1, right = (long) times[times.length - 1] * m;
        long mid = 0; // 이진 탐색에서 반드시 left, right, mid값을 사용해야 함
        long ans = right;

        while (left <= right) {
            mid = (left + right) / 2;

            // 이 시간이 가능하다면, 최소 시간을 갱신하고 더 작은 시간을 찾기 위해 right를 조정
            if (isPassed(times, mid) >= m) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    static long isPassed(int[] times,long mid) {
        long amount = 0; // 심사 받은 사람의 수

        for (int i = 0; i < times.length; i++) {
            amount += mid / times[i];
        }

        return amount;
    }
}
