import java.util.*;
import java.io.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;
        
        int answer = 0;
        List<String> list = new ArrayList<>();
        
        
        for(int i = 0 ; i < cities.length; i++){
            cities[i] = cities[i].toUpperCase();
            
            if(list.contains(cities[i])){
                answer +=1;
                list.remove(cities[i]);
                list.add(cities[i]);
            } else{
                answer +=5;
                if(list.size() < cacheSize){
                    list.add(cities[i]);
                } else{
                    list.remove(0);
                    list.add(cities[i]);
                }

            }
        }
        
        
        return answer;
    }
}