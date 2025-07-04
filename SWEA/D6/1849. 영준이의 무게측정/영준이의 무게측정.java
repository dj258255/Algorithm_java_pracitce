import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N, M;
    static int[] parent;
    static int[] weight;
    static int[] rank; //유니온파인드 rank.

    public static void input() throws IOException {
        //1~N(1INDEX
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //초기화
        parent = new int[N + 1];
        weight = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 0; //처음rank는 0
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String c = st.nextToken();
            if (c.equals("!")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                union(a, b, w);
            } else if (c.equals("?")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (find(a) != find(b)) {
                    bw.write("UNKNOWN ");
                } else {
                    bw.write((weight[a] - weight[b]) + " ");
                }
            }
        }
    }

    //경로압축 find
    static int find(int x) {
        if (parent[x] == x) return x;
        int root = find(parent[x]);
        weight[x] += weight[parent[x]];
        return parent[x] = root;
    }

    //union랭
    static void union(int a, int b, int w) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;

        //rank가 낮은걸 rank가 높은 트리에 연결
        if (rank[rootA] < rank[rootB]) {
            //rootA를 rootB에 연결
            parent[rootA] = rootB;
            weight[rootA] = weight[b] - weight[a] + w;
        } else if (rank[rootA] > rank[rootB]) {
            //rootB를 rootA에 연결
            parent[rootB] = rootA;
            weight[rootB] = weight[a] - weight[b] - w;
        } else {
            //rank가 같으면 아무거나 연결하고 rank 증가
            parent[rootA] = rootB;
            weight[rootA] = weight[b] - weight[a] + w;
            rank[rootB]++; // rootB의 rank 증가
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine().trim());
        for (int t=1; t<=T; t++) {
            bw.write("#" + t + " ");
            input();
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}