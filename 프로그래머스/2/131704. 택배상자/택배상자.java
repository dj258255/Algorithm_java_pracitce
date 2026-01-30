import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        int idx = 0;
        for (int i = 1; i <= order.length; i++) {
            
            if (i == order[idx]) {
                idx++;
                answer++;
                
                while (!stack.isEmpty() && idx < order.length && stack.peek() == order[idx]) {
                    stack.pop();
                    idx++;
                    answer++;
                }
                
            } else {
                stack.push(i);
            }
        }
        
        // 남은 보조 컨테이너 처리
        while (!stack.isEmpty() && idx < order.length && stack.peek() == order[idx]) {
            stack.pop();
            idx++;
            answer++;
        }
        
        return answer;
    }
}
