import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> scoreMap = new HashMap<>();
        char[][] types = {
            {'R', 'T'},
            {'C', 'F'},
            {'J', 'M'},
            {'A', 'N'}
        };

        //각 유형 초기화
        for (char[] pair : types) {
            scoreMap.put(pair[0], 0);
            scoreMap.put(pair[1], 0);
        }

        // 점수 계산
        for (int i = 0; i < survey.length; i++) {
            char disagree = survey[i].charAt(0); //매우 비동의
            char agree = survey[i].charAt(1); //동의
            int choice = choices[i];

            if (choice < 4) {
                scoreMap.put(disagree, scoreMap.get(disagree) + 4 - choice);
            } else if (choice > 4) {
                scoreMap.put(agree, scoreMap.get(agree) + choice - 4);
            }
        }

        // 최종 성격 유형 결정
        StringBuilder answerBuilder = new StringBuilder();
        for (char[] pair : types) {
            char first = pair[0];
            char second = pair[1];
            if (scoreMap.get(first) > scoreMap.get(second)) {
                answerBuilder.append(first);
            } else if (scoreMap.get(first) < scoreMap.get(second)) {
                answerBuilder.append(second);
            } else {
                // 같으면 사전순
                answerBuilder.append(first < second ? first : second);
            }
        }

        return answerBuilder.toString();
    }
}
