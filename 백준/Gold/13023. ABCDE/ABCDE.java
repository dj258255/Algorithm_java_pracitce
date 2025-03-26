import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Map<Integer, ArrayList<Integer>> friends;
    static boolean[] visited;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        

        friends = new HashMap<>();
        for (int i = 0; i < N; i++) {
            friends.put(i, new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends.get(a).add(b);
            friends.get(b).add(a);
        }
        
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            dfs(i, 0);
            if (found) break;
        }
        
        System.out.println(found ? 1 : 0);
    }
    
    
    static void dfs(int current, int depth) {
        if (depth == 4) {
            found = true;
            return;
        }
        
        visited[current] = true;
        for (int neighbor : friends.get(current)) {
            if (!visited[neighbor]) {
                dfs(neighbor, depth + 1);
                if (found) return;
            }
        }
        visited[current] = false;
    }
}
