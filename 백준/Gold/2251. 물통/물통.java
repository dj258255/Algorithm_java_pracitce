import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int A, B, C;
    static Set<Integer> answer;

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        answer = new TreeSet<>();
    }

    public static void solve() {
        boolean[][][] visited = new boolean[A+1][B+1][C+1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, C});
        visited[0][0][C] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int a = node[0];
            int b = node[1];
            int c = node[2];

            //A가 0이 되는 상태일 때 C의 물 양 기록
            if (a==0) answer.add(c);

            //A에서 B
            int small = Math.min(a,B-b);
            move(visited,queue,a-small,b+small,c);

            //A에서 C
            small = Math.min(a,C-c);
            move(visited,queue,a-small,b,c+small);

            //B에서 A
            small = Math.min(b,A-a);
            move(visited, queue,a+small,b-small, c);

            //B에서 C
            small = Math.min(b, C - c);
            move(visited, queue, a, b-small, c + small);

            //C에서 A
            small = Math.min(c, A - a);
            move(visited, queue, a+small, b, c -small);

            //C에서 B
            small = Math.min(c, B - b);
            move(visited, queue, a, b+small, c -small);
        }
    }

    static void move(boolean[][][] visited, Queue<int[]> queue, int a, int b, int c) {
        if (!visited[a][b][c]) {
            visited[a][b][c] = true;
            queue.add(new int[]{a,b,c});
        }
    }

    public static void output() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int v : answer) {
            sb.append(v).append(" ");
        }
        bw.write(sb.toString().trim());
        bw.newLine();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }
}