import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> term = new HashMap<>();
        for (String t : terms) {
            String[] split = t.split(" ");
            term.put(split[0], Integer.parseInt(split[1]));
        }
        
        List<Integer> expired = new ArrayList<>();
        //오늘 날짜를 일로 바꾸기
        int todayDate = toDate(today);

        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            String date = split[0];
            String type = split[1];

            int expiredDate = toDate(date) + term.get(type) * 28 - 1;
            if (expiredDate < todayDate) {
                expired.add(i + 1); // 1-based index
            }
        }

        // List<Integer> -> int[] 변환
        int[] answer = new int[expired.size()];
        for (int i = 0; i < expired.size(); i++) {
            answer[i] = expired.get(i);
        }
        return answer;
    }

    
    // 날짜를 "YYYY.MM.DD" -> days로 변환 (모든 달은 28일로 계산)
    private int toDate(String date) {
        String[] split = date.split("\\.");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        return year * 12 * 28 + month * 28 + day;
    }
}
