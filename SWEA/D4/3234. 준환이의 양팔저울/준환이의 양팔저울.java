import java.io.*;
import java.util.*;

public class Solution {
    static int N, res;
    static int[] weights;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            weights = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            used = new boolean[N];
            res = 0;
            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + res);
        }
    }

    private static void dfs(int idx, int sumL, int sumR) {
        if (idx == N) {  // 모든 추를 배치했다면
            res++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (used[i]) continue;
            used[i] = true;
            //추를 왼쪽에 올리는 경우 (항상 가능)
            dfs(idx + 1, sumL + weights[i], sumR);

            //추를 오른쪽에 올리는 경우 (오른쪽에 올린 후에도 왼쪽이 무겁거나 같아야 함)
            if (sumR + weights[i] <= sumL) {
                dfs(idx + 1, sumL, sumR + weights[i]);
            }
            used[i] = false;
        }
    }
}
