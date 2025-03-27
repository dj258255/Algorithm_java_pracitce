import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Solution {
    
    static int[] parents;
    static int N; 
    
    static void make() {
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }
    
    static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }
    
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
    
    static class Edge implements Comparable<Edge> {
        int from, to;
        long weight;
        
        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            parents = new int[N];
            make();
            
            long[] x = new long[N];
            long[] y = new long[N];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }
            
            double E = Double.parseDouble(br.readLine().trim());
            
            int edgeCount = N * (N - 1) / 2;
            Edge[] edges = new Edge[edgeCount];
            int idx = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];
                    long distSquared = dx * dx + dy * dy;
                    edges[idx++] = new Edge(i, j, distSquared);
                }
            }
            
            Arrays.sort(edges);
            
            long mstCost = 0;
            int edgeUsed = 0;
            for (Edge edge : edges) {
                if (union(edge.from, edge.to)) {
                    mstCost += edge.weight;
                    edgeUsed++;
                    if (edgeUsed == N - 1) break;
                }
            }
            

            long result = Math.round(mstCost * E);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}
