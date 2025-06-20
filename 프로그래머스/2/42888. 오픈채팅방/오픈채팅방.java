import java.util.Map;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        
        //record -> 첫 단어 "Enter" , "Leave" , "Change"
        //두번째 [유저아이디]
        //세번째 [낙네임] 
        
        int count = 0;
        Map<String, String> map = new HashMap<>();
        
        for (int i = 0 ; i < record.length; i++){
            String[] temp = record[i].split(" ");
            
            if (temp[0].equals("Leave")){
                continue;
            } else if (temp[0].equals("Enter")){
                map.put(temp[1], temp[2]);
            } else { //Change
                map.put(temp[1], temp[2]);
                count++;
            }
        }
        
        String[] answer = new String[record.length - count];
        
        int idx = 0;
        
        for(int i = 0 ; i < record.length; i++){
            String[] temp = record[i].split(" ");
            String nickname = map.get(temp[1]);
            
            if(temp[0].equals("Enter")){
                answer[idx] = nickname + "님이 들어왔습니다.";
                idx++;
            } else if(temp[0].equals("Leave")){
                answer[idx] = nickname + "님이 나갔습니다.";
                idx++;
            }

        }
        
        return answer;
    }
}
//리스트 닉네임 아이디 매핑