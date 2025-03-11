import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long K = Long.parseLong(st.nextToken());
        long N = Long.parseLong(st.nextToken());
        long right = 0;
        long[] number = new long[(int)K];

        for (int i = 0; i < (int)K; i++) {
            st = new StringTokenizer(br.readLine());
            number[i] = Long.parseLong(st.nextToken());
            right += number[i];
        }
        right /= N;
        long answer = 0;
        long left = 1;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            for (int i = 0; i < (int)K; i++) {
                count += number[i] / mid;
            }

            if (count >= N) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
