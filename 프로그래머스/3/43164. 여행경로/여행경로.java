import java.util.*;

class Solution {
    public static List<String> route = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for (String[] ticket : tickets) {
            map.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }

        dfs("ICN", map);

        Collections.reverse(route); // 또는 route.add(0, airport) 방식으로도 가능
        return route.toArray(new String[0]);
    }

    public static void dfs(String airport, Map<String, PriorityQueue<String>> map) {
        PriorityQueue<String> pq = map.get(airport);

        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll(), map);
        }

        route.add(airport); // 후위 순회 방식으로 삽입
    }
}
