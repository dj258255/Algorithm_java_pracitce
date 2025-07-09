import java.util.*;

class Solution {
    Map<String, List<Integer>> infoMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        
        for (String inf : info) {
            String[] parts = inf.split(" ");
            dfs(parts, "", 0);
        }
        
        //스코어 정렬
        for (List<Integer> scores : infoMap.values()) {
            Collections.sort(scores);
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String q = query[i].replaceAll(" and ", " ");
            String[] parts = q.split(" ");
            String key = parts[0] + parts[1] + parts[2] + parts[3];
            int score = Integer.parseInt(parts[4]);

            if (infoMap.containsKey(key)) {
                List<Integer> scores = infoMap.get(key);
                //이분탐색
                int idx = lowerBound(scores, score);
                answer[i] = scores.size() - idx;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    // 모든 조합을 저장
    private void dfs(String[] parts, String str, int depth) {
        if (depth == 4) {
            infoMap.computeIfAbsent(str, 
                                    k -> new ArrayList<>())
                   .add(Integer.parseInt(parts[4]));
            return;
        }
        dfs(parts, str + "-", depth+1); //조건 미포함
        dfs(parts, str + parts[depth], depth+1); //조건 포함
    }

    //이분탐색
    private int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
