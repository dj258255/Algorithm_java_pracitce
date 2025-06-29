import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long q1Sum = 0;
        long q2Sum = 0;
        
        // 큐 초기화 및 합 계산
        for(int i = 0; i < queue1.length; i++){
            q1.add(queue1[i]);
            q1Sum += queue1[i];
        }
        
        for(int i = 0 ; i < queue2.length; i++){
            q2.add(queue2[i]);
            q2Sum += queue2[i];
        }
        
        long totalSum = q1Sum + q2Sum;
        
        // 전체 합이 홀수면 불가능
        if(totalSum % 2 != 0) return -1;
        
        long target = totalSum / 2;
        
        // 이미 같으면 0 리턴
        if(q1Sum == target) return 0;
        
        // 최대 연산 횟수: 각 원소가 최대 2번의 큐를 거쳐갈 수 있음
        int maxOperations = queue1.length * 3;
        int operations = 0;
        
        // 투 포인터 방식으로 해결
        while(operations < maxOperations) {
            if(q1Sum == target) {
                return operations;
            }
            
            if(q1Sum > target) {
                // q1에서 q2로 이동
                if(q1.isEmpty()) break;
                int element = q1.poll();
                q2.add(element);
                q1Sum -= element;
                q2Sum += element;
            } else {
                // q2에서 q1으로 이동  
                if(q2.isEmpty()) break;
                int element = q2.poll();
                q1.add(element);
                q1Sum += element;
                q2Sum -= element;
            }
            
            operations++;
        }
        
        return -1;
    }
}