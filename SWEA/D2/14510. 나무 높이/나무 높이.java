import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= Tc; t++) {
            // 입력
            int N = Integer.parseInt(br.readLine().trim());
            int[] trees = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int highTree = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                if (trees[i] > highTree) {
                    highTree = trees[i];
                }
            }
            
            // 그리디 계산
            int oddDay = 0, evenDay = 0;
            for (int i = 0; i < N; i++) {
                int diff = highTree - trees[i];
                oddDay  += diff % 2;
                evenDay += diff / 2;
            }
            // 짝수일 물주기 과잉분을 홀수일 물주기로 전환
            while (evenDay - oddDay > 1) {
                evenDay--;
                oddDay += 2;
            }
            int result = (oddDay > evenDay)
                       ? 2 * oddDay - 1
                       : 2 * evenDay;
            
            // 출력
            System.out.println("#" + t + " " + result);
        }
        br.close();
    }
}
