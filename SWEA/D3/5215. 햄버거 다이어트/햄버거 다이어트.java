import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, L;
    static int maxTaste;
    static int[][] food;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  //개수
            L = Integer.parseInt(st.nextToken()); //허용 칼로리
            
            food = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                food[i][0] = Integer.parseInt(st.nextToken()); //점수
                food[i][1] = Integer.parseInt(st.nextToken()); //칼로리
            }
            
            maxTaste = 0; //최대 맛
            dfs(0, 0, 0); //조합
            System.out.println("#" + tc + " " + maxTaste);
        }
    }
    

    public static void dfs(int index, int taste, int calories) {

        if (calories > L) {
            return;
        }
        
        if (index == N) {
            maxTaste = Math.max(maxTaste, taste);
            return;
        }
        dfs(index + 1, taste, calories);
        
        dfs(index + 1, taste + food[index][0], calories + food[index][1]);
    }
}
