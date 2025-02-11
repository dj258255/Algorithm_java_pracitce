import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;

public class Main {
    static int N;// 구역의 개수
    static int[] peo;//각 구역의 인구
    static Map<Integer, ArrayList<Integer>> map;//인접 구역
    static boolean[] sel;// true : 그룹 A, false : 그룹 B
    static int answer = Integer.MAX_VALUE; // 최소 인구 차이

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //구역의 개수
        peo = new int[N+1]; //각 구역의 인구
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            peo[i+1] = Integer.parseInt(st.nextToken());
        }

        map = new HashMap<>();
        for (int i = 1; i <= N; i++){
            map.put(i, new ArrayList<>());
        }
        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken());
            for (int k = 0; k < temp; k++){
                map.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
        // true면 그룹A false면 그룹 B
        sel = new boolean[N+1];
        //1번은 항상 그룹A
        sel[1] = true;
        dfs(2);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    //2번부터 그룹A/B결정
    static void dfs(int idx) {
        if (idx > N) { // 모든 구역에 대한 그룹 배정이 완료되었을 때, 두 그룹의 구역 번호 리스트 생성
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();
            for (int i = 1; i <= N; i++){
                if (sel[i]){
                    groupA.add(i);
                } else {
                    groupB.add(i);
                }
            }
            // 한쪽 그룹이라도 비어있으면 무시
            if (groupA.isEmpty() || groupB.isEmpty()) return;

            // BFS를 이용해 각 그룹 내에서 연결된 구역의 수 확인
            int countA = bfs(groupA.get(0), true);
            int countB = bfs(groupB.get(0), false);
            if (countA == groupA.size() && countB == groupB.size()) {
                int sumA = 0, sumB = 0;
                for (int num : groupA) sumA += peo[num];
                for (int num : groupB) sumB += peo[num];
                answer = Math.min(answer, Math.abs(sumA - sumB));
            }
            return;
        }

        // 그룹 A에 배정하는 경우
        sel[idx] = true;
        dfs(idx + 1);

        // 그룹 B에 배정하는 경우
        sel[idx] = false;
        dfs(idx + 1);
    }

    // BFS를 이용해, 시작 구역에서 같은 그룹에 속한 구역들을 방문하고, 방문한 개수를 반환
    public static int bfs(int start, boolean group) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        queue.add(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()){
            int node = queue.poll();
            for (int neighbor : map.get(node)){
                // 인접 구역이 아직 방문되지 않았고, 같은 그룹에 속해 있으면 방문
                if (!visited[neighbor] && sel[neighbor] == group) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }
        return count;
    }
}
