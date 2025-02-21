import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int count;
    static int N, M;
    static Map<Integer, Set<Integer>> Map;
    // DFS 진행 시, 각 원소가 선택되었는지를 기록 (인덱스 0부터, 실제 번호는 index+1)
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            Map = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                Map.put(i, new HashSet<>());
            }
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                Map.get(a).add(b);
                Map.get(b).add(a);
            }
            
            count = 0;
            selected = new boolean[N];
            dfs(0);
            System.out.println("#" + tc + " " + count);
        }
    }

    //index:현재 고려 중인 원소의 인덱스 (실제 번호는 index+1)
    public static void dfs(int index) {
        //모든 원소에 대해 선택 여부를 결정했으면 경우의 수 1 증가
        if (index == N) {
            count++;
            return;
        }
        
        //현재 원소를 선택하지 않는 경우 다음 원소로 넘어감
        dfs(index + 1);
        
        //현재 원소를 선택할 수 있는지 확인
        boolean canSelect = true;
        //이전에 선택한 원소들과의 충돌 여부 검사
        for (int i = 0; i < index; i++) {
            if (selected[i]) { //i번째 원소가 선택되었으면
                //이전에 선택한 원소와 현재 원소 사이에 충돌 관계가 있는지 확인
                if (Map.get(i + 1).contains(index + 1)) {
                    canSelect = false;
                    break;
                }
            }
        }
        //충돌이 없으면 현재 원소를 선택한 후 DFS 탐색 진행
        if (canSelect) {
            selected[index] = true;   // 현재 원소 선택
            dfs(index + 1);           // 다음 원소로 DFS 진행
            selected[index] = false;  // DFS 후 원소 선택 상태 복구 (백트래킹)
        }
    }
}
