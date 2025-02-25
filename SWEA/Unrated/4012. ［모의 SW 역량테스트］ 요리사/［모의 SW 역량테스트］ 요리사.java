import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] synergy;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            synergy = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;
            //비트마스크로 모든 가능한 조합을 시도
            find(0, 0, new boolean[N]);
            System.out.println("#" + tc + " " + answer);
        }
    }

    //그룹나누기
    public static void find(int idx, int count, boolean[] selected) {
        //N/2개의 재료를 선택하면 그룹 A가 완성됨
        if (count == N / 2) {//그룹완성되면 로직 시작
            int groupA = 0, groupB = 0;

            //그룹 A와 그룹 B의 시너지 계산
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (selected[i] && selected[j]) { //그룹 A
                        groupA += synergy[i][j] + synergy[j][i];
                    } else if (!selected[i] && !selected[j]) {//그룹 B
                        groupB += synergy[i][j] + synergy[j][i];
                    }
                }
            }

            answer = Math.min(answer, Math.abs(groupA - groupB));
            return;
        }

        //N개의 재료 중 하나를 선택하고 그룹에 추가
        for (int i = idx; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                find(i + 1, count + 1, selected);
                selected[i] = false;
            }
        }
    }
}
