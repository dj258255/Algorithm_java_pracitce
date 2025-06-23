
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");
        int N = Integer.parseInt(first[0]);
        int M = Integer.parseInt(first[1]);

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(A);

        int minDiff = Integer.MAX_VALUE;
        int i = 0, j = 0;

        while (j < N && i < N) {
            int diff = A[j] - A[i];

            if (i == j) {
                j++; // 두 포인터가 같으면 j 먼저 증가
                continue;
            }

            if (diff < M) {
                j++;
            } else {
                minDiff = Math.min(minDiff, diff);
                i++;
            }
        }


        System.out.println(minDiff);
    }
}
