import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] cal = new int[4]; // +,-,*,/
    static int[] number;  //게임 판에 적힌 숫자들
    static int N;
    static int maxResult, minResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            number = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                cal[i] = Integer.parseInt(st.nextToken());
            }

            //숫자들을 한 줄에 공백으로 구분하여 읽기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                number[i] = Integer.parseInt(st.nextToken());
            }

            maxResult = Integer.MIN_VALUE;
            minResult = Integer.MAX_VALUE;
            //첫번째 숫자를 초기값으로 설정하고 index 1부터 DFS 진행
            dfs(1, number[0]);

            int diff = maxResult - minResult;
            System.out.println("#" + tc + " " + diff);
        }
    }

    public static void dfs(int index, int current) {
        if (index == N) {
            maxResult = Math.max(maxResult, current);
            minResult = Math.min(minResult, current);
            return;
        }
        for (int op = 0; op < 4; op++) {
            if (cal[op] > 0) {
                cal[op]--;
                int next = 0;
                switch (op) {
                    case 0:
                        next = current + number[index];
                        break;
                    case 1:
                        next = current - number[index];
                        break;
                    case 2:
                        next = current * number[index];
                        break;
                    case 3:
                        if (current < 0) {
                            next = - (Math.abs(current) / number[index]);
                        } else {
                            next = current / number[index];
                        }
                        break;
                }
                dfs(index + 1, next);
                cal[op]++;  // 원상복구
            }
        }
    }
}
