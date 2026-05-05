import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length; // 논문 n 편
        Arrays.sort(citations); //오름차순
        
        int answer = 0;
        //h의 최대값 구하기 
        for(int i = 0; i < n; i++){
            int curH = n-i; //현재 h 인덱스로 지정
            //01356
            if(citations[i] >= curH){
                answer = Math.max(answer,curH);
            }
            
        }
        
        
        
        
        
        return answer;
    }
}