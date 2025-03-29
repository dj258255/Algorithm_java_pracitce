import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans = Integer.MAX_VALUE;
    static ArrayList<int[]> houses = new ArrayList<>();
    static ArrayList<int[]> chickens = new ArrayList<>();
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                int val = Integer.parseInt(st.nextToken());
                if(val == 1) {
                    houses.add(new int[]{i, j});
                } else if(val == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }
        
        selected = new boolean[chickens.size()];
        dfs(0, 0);
        
        System.out.println(ans);
    }
    
    static void dfs(int idx, int cnt) {
        if(cnt == M) {
            int sum = 0;
            for (int[] h : houses) {
                int d = Integer.MAX_VALUE;
                for (int i = 0; i < chickens.size(); i++) {
                    if(selected[i]) {
                        int[] c = chickens.get(i);
                        d = Math.min(d, Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]));
                    }
                }
                sum += d;
            }
            ans = Math.min(ans, sum);
            return;
        }
        if(idx == chickens.size()) return;
        
        // 치킨집 선택하는 경우
        selected[idx] = true;
        dfs(idx + 1, cnt + 1);
        
        // 치킨집 선택하지 않는 경우
        selected[idx] = false;
        dfs(idx + 1, cnt);
    }
}
