import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Solution {
    public static List<String> route = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for(int i = 0 ; i < tickets.length; i++){
            String start = tickets[i][0];
            String goal = tickets[i][1];
            
            if(!map.containsKey(start)){
                map.put(start, new PriorityQueue<>());
            }
            map.get(start).add(goal);
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
