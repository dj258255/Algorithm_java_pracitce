import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] numbers;
    static int N, M;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        find(0, 0, 0);

        System.out.println(answer);
    }

    public static void find(int index, int sum, int start) {
        if (index == 3) {
            if (sum <= M && sum > answer) {
                answer = sum;
            }
            return;
        }


        for (int i = start; i < N; i++) {
            find(index + 1, sum + numbers[i], i + 1);
        }
    }
}
