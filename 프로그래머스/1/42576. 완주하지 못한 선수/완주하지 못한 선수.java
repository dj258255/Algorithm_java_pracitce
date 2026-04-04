import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String,Integer> participants = new HashMap<>();
        for(int i = 0 ; i < participant.length; i++){
         //   participants.computeIfAbsnet(participant[i] , participants.getOrDefault(0,));
            participants.put(participant[i], participants.getOrDefault(participant[i],0)+1);
        }
        
        for(int i = 0 ; i < completion.length; i++){
            participants.put(completion[i] , participants.get(completion[i])-1);
        }
        
        for(String name : participants.keySet()){
            if(participants.get(name) > 0){
                answer = name;
            }
        }
        
        return answer;
    }
}