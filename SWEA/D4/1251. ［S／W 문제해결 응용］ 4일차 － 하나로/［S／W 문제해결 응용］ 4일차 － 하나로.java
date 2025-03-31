import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            long[] x = new long[N];
            long[] y = new long[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }
            
            double E = Double.parseDouble(br.readLine());
            
            boolean[] visited = new boolean[N];
            double[] minDist = new double[N];
            Arrays.fill(minDist, Double.MAX_VALUE);

            minDist[0] = 0;
            double result = 0;
            
            for (int i = 0; i < N; i++) {
                int u = -1;
                double min = Double.MAX_VALUE;
                for (int j = 0; j < N; j++) {
                    if (!visited[j] && minDist[j] < min) {
                        min = minDist[j];
                        u = j;
                    }
                }
                
                visited[u] = true;
                result += min;
                
                for (int v = 0; v < N; v++) {
                    if (!visited[v]) {
                        long dx = x[u] - x[v];
                        long dy = y[u] - y[v];
                        double cost = dx * dx + dy * dy;
                        if (cost < minDist[v]) {
                            minDist[v] = cost;
                        }
                    }
                }
            }
            
            long answer = Math.round(result * E);
            System.out.println("#" + t + " " + answer);
        }
    }
}
