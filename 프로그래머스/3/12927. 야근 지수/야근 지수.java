import java.util.*;
import java.io.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
        (a,b) -> b-a
        );
        
        for(int i : works){
            pq.add(i);
        }
        
        
        while(n > 0 && !pq.isEmpty()){
            int max = pq.poll();
            if(max==0) break;
            max -= 1;
            n -=1;
            pq.add(max);
        }
        
        long answer =0;
        while(!pq.isEmpty()){
            int node = pq.poll();
            
            answer += node*node;
        }
        return answer;
    }
}