import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int sco : scoville){
            pq.add(sco);
        }
        
        while(!pq.isEmpty()){
            if(pq.peek() >= K) return answer;
            if(pq.size() < 2) return -1;
            int sco1 = pq.poll();
            int sco2 = pq.poll();
            answer++;
            
            pq.add(sco1+(sco2*2));
            
        }
        //섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
        
        
        return answer;
    }
}