import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Solution {
    
    private Map<Integer, ArrayList<Integer>> graph;
    private Map<Integer, Integer> inDegree;
    private Map<Integer, Integer> outDegree;
    
    public int[] solution(int[][] edges) {
        // 그래프 초기화
        buildGraph(edges);
        
        // 정점 찾기 (들어오는 간선 0개, 나가는 간선 2개 이상)
        int vertex = findVertex();
        
        int[] answer = new int[4];
        answer[0] = vertex;
        
        // 정점에서 시작하는 각 연결 요소 분석
        analyzeComponents(vertex, answer);
        
        return answer;
    }
    
    private void buildGraph(int[][] edges) {
        graph = new HashMap<>();
        inDegree = new HashMap<>();
        outDegree = new HashMap<>();
        
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            outDegree.put(from, outDegree.getOrDefault(from, 0) + 1);
            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
            
            // 모든 노드를 맵에 등록
            if (!inDegree.containsKey(from)) inDegree.put(from, 0);
            if (!outDegree.containsKey(to)) outDegree.put(to, 0);
        }
    }
    
    private int findVertex() {
        for (int node : outDegree.keySet()) {
            if (inDegree.getOrDefault(node, 0) == 0 && outDegree.get(node) >= 2) {
                return node;
            }
        }
        return -1;
    }
    
    private void analyzeComponents(int vertex, int[] answer) {
        Set<Integer> visited = new HashSet<>();
        
        for (int start : graph.getOrDefault(vertex, new ArrayList<>())) {
            if (visited.contains(start)) continue;
            
            // 연결 요소 탐색
            Set<Integer> component = new HashSet<>();
            Queue<Integer> queue = new ArrayDeque<>();
            
            queue.add(start);
            component.add(start);
            visited.add(start);
            
            while (!queue.isEmpty()) {
                int current = queue.poll();
                
                for (int next : graph.getOrDefault(current, new ArrayList<>())) {
                    if (next != vertex && !visited.contains(next)) {
                        visited.add(next);
                        component.add(next);
                        queue.add(next);
                    }
                }
            }
            
            // 그래프 형태 분류
            classifyGraph(component, vertex, answer);
        }
    }
    
    private void classifyGraph(Set<Integer> component, int vertex, int[] answer) {
        int nodeCount = component.size();
        int edgeCount = 0;
        int degreeZeroCount = 0; // 진출 차수가 0인 노드 수
        int degreeTwoCount = 0;  // 진출 차수가 2인 노드 수
        
        for (int node : component) {
            int currentOutDegree = 0;
            
            // 컴포넌트 내부 간선만 계산
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (component.contains(neighbor) || neighbor == node) {
                    currentOutDegree++;
                    edgeCount++;
                }
            }
            
            if (currentOutDegree == 0) degreeZeroCount++;
            if (currentOutDegree == 2) degreeTwoCount++;
        }
        
        // 그래프 형태 판별
        if (nodeCount == 1) {
            // 단일 노드의 경우
            if (edgeCount == 1) {
                answer[1]++; // 도넛 (자기 자신으로의 간선)
            } else {
                answer[2]++; // 막대 (간선 없음)
            }
        } else if (degreeZeroCount == 1) {
            // 진출 차수가 0인 노드가 1개
            if (edgeCount == nodeCount - 1) {
                answer[2]++; // 막대
            } else {
                answer[1]++; // 도넛
            }
        } else if (degreeTwoCount > 0) {
            answer[3]++; // 8자
        } else {
            answer[1]++; // 도넛 (기본값)
        }
    }
}