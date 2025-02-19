import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int winCount, loseCount;
    static int[] a;
    static int[] b;
    static boolean[] used;
    static final int rounds = 9; // 총 라운드 수

    public static void dfs(int idx, int scoreA, int scoreB) {
        if (idx == rounds) {
            if (scoreA > scoreB) {
                winCount++;
            } else {
                loseCount++;
            }
            return;
        }
        
        // 인영이가 낼 카드의 순서를 하나씩 선택 (총 9! 경우의 수)
//        높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고,
//
//        낮은 수가 적힌 카드를 낸 사람은 아무런 점수도 얻을 수 없다.
        for (int i = 0; i < rounds; i++) {
            if (!used[i]) {
                used[i] = true;
                int roundSum = a[idx] + b[i];
                if (a[idx] > b[i]) {
                    dfs(idx + 1, scoreA + roundSum, scoreB);
                } else {
                    dfs(idx + 1, scoreA, scoreB + roundSum);
                }
                used[i] = false;
            }
        }
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken()); 

        for (int tc = 1; tc <= T; tc++) {
            a = new int[rounds];
            b = new int[rounds];
            used = new boolean[rounds];
            
            //규영이
            st = new StringTokenizer(br.readLine());
            boolean[] check = new boolean[19];

            for (int i = 0; i < rounds; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                check[a[i]] = true;
            }
            
            int idx = 0;
            for (int i = 1; i <= 18; i++) {
                if (!check[i]) {
                    b[idx++] = i;
                }
            }
            
            winCount = 0;
            loseCount = 0;
            
            dfs(0, 0, 0);
            
            System.out.println("#" + tc + " " + winCount + " " + loseCount);
        }
    }
}
