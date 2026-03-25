import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int[] rope;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        rope = new int[N+1];
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0 ; i < N; i++){
            rope[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void solve() throws IOException {
        int[] tails = new int[N];
        int size = 0;

        for (int i = 0; i < N; i++) {
            int val = rope[i];

            //tails에서 val이 들어갈 위치 이분탐색 (lower_bound)
            int left = 0, right = size;
            while (left < right) {
                int mid = (left + right) / 2;
                if (tails[mid] < val) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            tails[left] = val;
            if (left == size) size++;  // 새로운 길이 갱신
        }

        bw.write("" + size);
    }

    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }
}