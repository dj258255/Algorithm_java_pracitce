import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        ArrayList<String> messages = new ArrayList<>();

        for (String log : record) {
            String[] parts = log.split(" ");
            String status = parts[0];
            String uid = parts[1];

            if (status.equals("Enter") || status.equals("Change")) {
                String nickname = parts[2];
                map.put(uid, nickname);
            }
        }

        for (String log : record) {
            String[] parts = log.split(" ");
            String status = parts[0];
            String uid = parts[1];

            if (status.equals("Enter")) {
                messages.add(map.get(uid) + "님이 들어왔습니다.");
            } else if (status.equals("Leave")) {
                messages.add(map.get(uid) + "님이 나갔습니다.");
            }
        }

        return messages.toArray(new String[0]);
    }
}
