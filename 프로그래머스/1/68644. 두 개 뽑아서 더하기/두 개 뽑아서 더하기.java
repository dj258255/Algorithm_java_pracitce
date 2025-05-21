import java.util.ArrayList;
import java.util.Comparator;

class Solution {
    public ArrayList<Integer> solution(int[] numbers) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        int N = numbers.length;
        for(int i = 0 ; i < N-1; i++){
            for(int j = i+1 ; j < N; j++){
                
                if(i==j)
                    continue;
                
                if(!answer.contains(numbers[i]+numbers[j]) ){
                    answer.add(numbers[i]+numbers[j]);
                }
            }
        }
        
        answer.sort(Comparator.naturalOrder());
        
        return answer;
    }
}