import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 401;
    static boolean[][] graph = new boolean[MAX][MAX]; //i -> j 관계 있으면 true

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //사건 개수
        int k = Integer.parseInt(st.nextToken()); //관계 개수

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from][to] = true; //from사건이 to사건보다 먼저
        }

        // 플로이드-워셜 알고리즘
        for (int mid = 1; mid <= n; mid++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][mid] && graph[mid][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        int s = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (graph[a][b]) {
                sb.append("-1\n"); //a가 b보다 먼저
            } else if (graph[b][a]) {
                sb.append("1\n"); //b가 a보다 먼저
            } else {
                sb.append("0\n"); //알 수 없음
            }
        }

        System.out.print(sb);
    }
}
