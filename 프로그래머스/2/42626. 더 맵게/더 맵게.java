import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i = 0 ; i < scoville.length; i++){
            pq.add(scoville[i]);
        }
        
        int answer =0;
        
        while(!pq.isEmpty() && pq.peek()<K){
            if (pq.size() < 2) {
                return -1;
            }
            
            int first = pq.poll();
            int second = pq.poll();
            
            int temp = first+(2*second);
            pq.offer(temp);
            
            answer++;
        }
        
        return answer;
    }
}