import java.util.HashMap;
import java.util.Map;


class Solution {
    public boolean solution(String[] phoneBook) {
        boolean answer = true;

            Map<String, Integer> map = new HashMap<>();

            for(int i = 0; i < phoneBook.length; i++) {
                map.put(phoneBook[i], i);
            }


            for(int i = 0; i < phoneBook.length; i++) {
                for(int j = 0; j < phoneBook[i].length(); j++) {
                    if(map.containsKey(phoneBook[i].substring(0,j))) {
                        answer = false;
                        return answer;
                    }
                }
            }




            return answer;
    }
}
