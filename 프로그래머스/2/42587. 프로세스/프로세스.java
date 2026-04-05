import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> queue = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(
        (a,b) ->b-a
        );
        
        for(int i = 0 ; i < priorities.length; i++){
            queue.add(new int[]{priorities[i],i}); //우선순위랑 현재 순위
            pq.add(priorities[i]);
        }
        
        int answer = 0;
        
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int max = pq.peek(); //실행할 최대 값
            int curValue = node[0]; //현재 우선순위 값 
            int order = node[1]; //현재 순서
            
            if(max != curValue){ //실행할 최대값이 아니면 다시넣기
                queue.add(new int[]{curValue,order});
                continue;
            }
            
            pq.poll();
            answer++;
            
            
            if(order == location){ //순서랑 location이랑 맞다면 
                break;
            }
        }
        
        return answer;
    }
}