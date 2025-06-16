import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = bfs(numbers,target);
        return answer;
    }
    
    public static int bfs(int[] numbers, int target){
        int count =0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[numbers.length];
        queue.add(new int[]{0,0}); // 인덱스와 , 현재 숫자
        
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            
            int depth = node[0]; //몇번째 인덱스
            int current = node[1]; //현재 덧셈 뺄셈 한숫자
            
            if (depth == numbers.length) {
                if (current == target) count++;
                continue;
            }
            
            queue.add(new int[]{depth+1,current+numbers[depth]});
            queue.add(new int[]{depth+1,current-numbers[depth]});
        }
        
        return count;
    }
}