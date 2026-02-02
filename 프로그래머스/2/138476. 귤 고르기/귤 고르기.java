import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 1. 크기별 개수 카운팅
        Map<Integer, Integer> count = new HashMap<>();
        for (int t : tangerine) {
            count.put(t, count.getOrDefault(t, 0) + 1);
        }
        
        // 2. 개수만 뽑아서 내림차순 정렬
        List<Integer> counts = new ArrayList<>(count.values());
        counts.sort(Collections.reverseOrder());
        
        // 3. 많은 것부터 k개 채우기
        int answer = 0;
        for (int c : counts) {
            k -= c;
            answer++;
            if (k <= 0) break;
        }
        
        return answer;
    }
}