import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] earthworm;
    static long totalX, totalY, answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            earthworm = new int[N][2];
            totalX = 0;
            totalY = 0;
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                earthworm[i][0] = Integer.parseInt(st.nextToken());
                earthworm[i][1] = Integer.parseInt(st.nextToken());
                totalX += earthworm[i][0];
                totalY += earthworm[i][1];
            }
            
            answer = Long.MAX_VALUE;
            dfs(0, 0, 0, 0);
            
            System.out.println("#" + tc + " " + answer);
        }
    }
    
    public static void dfs(int idx, int count, long sumX, long sumY) {
    	//선택된 그룹 합 : SumX,sumY
    	//선택안된 그룹 합 : totalX - sumX , totalY - sumY
        if (count == N / 2) {
            long dx = 2 * sumX - totalX;
            //long dx = sumX -(totalX-sumX);
            long dy = 2 * sumY - totalY;
            //long dy = sumY -(totalY-sumY);
            
            long current = dx * dx + dy * dy;
            answer = Math.min(answer, current);
            return;
        }
        
        
        for (int i = idx; i < N; i++) {
            dfs(i + 1, count + 1, sumX + earthworm[i][0], sumY + earthworm[i][1]);
        }
    }
}
