import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, HashSet<String>> map = new HashMap<>();
        
        for(int i = 0 ; i < clothes.length; i++){
            String clothName = clothes[i][0]; //의상의 이름
            String clothType = clothes[i][1]; //의상의 종류 
            map.computeIfAbsent(clothType, k -> new HashSet<>()).add(clothName); //Set이 중복 자동 제거
        }
        

        for (HashSet<String> set : map.values()) {
            answer *= (set.size() + 1);
        }
        answer -=1;
        return answer;
    }
}