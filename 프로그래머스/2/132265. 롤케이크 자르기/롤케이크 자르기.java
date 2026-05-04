import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] topping) {
        int answer =0;
        //토핑들이 일렬로 있고 공평하게 자르는 방법의 수 return
        
        Map<Integer,Integer> leftMap = new HashMap<>(); //토핑 타입 , 개수
        Map<Integer,Integer> rightMap = new HashMap<>();
        for(int i = 0 ; i < topping.length; i++){
            int toppingType = topping[i];
            rightMap.put(toppingType, rightMap.getOrDefault(toppingType, 0)+1);
        }
        
        for(int i = 0 ; i < topping.length; i++){
            int toppingType = topping[i];
            rightMap.put(toppingType, rightMap.getOrDefault(toppingType, 0) -1);
            leftMap.put(toppingType, leftMap.getOrDefault(toppingType,0) +1);
            if (rightMap.get(toppingType) == 0) {
                rightMap.remove(toppingType);
            }
            if(rightMap.size() == leftMap.size()) answer++;
        }
        
        return answer;
    }
}