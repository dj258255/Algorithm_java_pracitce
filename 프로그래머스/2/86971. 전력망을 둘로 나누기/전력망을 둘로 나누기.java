import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        // 그래프 구성: 각 노드에 인접한 노드 리스트 생성
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        // 각 전선을 하나씩 끊어보기
        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            
            // 전선 끊기: a와 b 사이의 연결 제거
            graph.get(a).remove(Integer.valueOf(b));
            graph.get(b).remove(Integer.valueOf(a));
            
            // a를 시작점으로 BFS 실행하여 한 그룹의 크기를 계산
            int groupSize = bfs(a, graph, n);
            int diff = Math.abs(groupSize - (n - groupSize));
            answer = Math.min(answer, diff);
            
            // 끊었던 전선을 다시 복구하여 원래 그래프 상태로 복원
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        return answer;
    }
    
    // BFS: 시작 노드부터 탐색하여 연결된 노드의 개수를 반환
    private int bfs(int start, Map<Integer, ArrayList<Integer>> graph, int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        
        visited[start] = true;
        queue.add(start);
        int count = 1;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }
        return count;
    }
}
