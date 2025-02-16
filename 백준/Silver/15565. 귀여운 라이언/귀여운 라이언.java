import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] list = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        int minLen = Integer.MAX_VALUE;
        int count = 0;
        int start = 0;

        for (int end = 0; end < N; end++) {
            if (list[end] == 1) {//라이언 인형일 경우
                count++;
            }

            while (count >= K) { //현재 라이언 인형이 K개 이상이면
                minLen = Math.min(minLen, end - start + 1); //길이 계산
                if (list[start] == 1) {
                    count--;
                }
                start++;
            }
        }

        System.out.println(minLen == Integer.MAX_VALUE ? -1 : minLen);
    }
}
