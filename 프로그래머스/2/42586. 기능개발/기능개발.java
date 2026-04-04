import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        int[] days = new int[progresses.length];
        for(int i = 0 ; i < progresses.length; i++){
            days[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }
        
        
        int index = 0;
        while(index < days.length){
            int maxDay = days[index];
            int count =0;
            while(index < days.length && days[index] <= maxDay){
                count++;
                index++;
            }
            
            result.add(count);
        }
        int[] answer = new int[result.size()];
        for(int i = 0 ; i < answer.length; i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}