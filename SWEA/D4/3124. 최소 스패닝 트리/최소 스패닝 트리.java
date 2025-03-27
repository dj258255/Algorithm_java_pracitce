import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Solution {
    static int V, E;
    static int[] parent;
    
    static void make() {
        parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }
    }
    
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootB] = rootA;
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            make();
            
            int[][] edges = new int[E][3];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                from--;
                to--;
                edges[i][0] = from;
                edges[i][1] = to;
                edges[i][2] = weight;
            }

            Arrays.sort(edges, (a, b) -> a[2] - b[2]);

            
            long answer = 0;
            int count = 0;

            for (int i = 0; i < E; i++) {
                if (union(edges[i][0], edges[i][1])) {
                    answer += edges[i][2];
                    count++;
                    if (count == V - 1) break;
                }
            }
            

            
            System.out.println("#" + tc + " " + answer);
        }
    }
}
