import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Arrays.sort(tangerine);
        int[] arr = new int[tangerine[tangerine.length - 1]];
        
        for(int i = 0 ; i < tangerine.length ; i++){
            arr[tangerine[i] - 1]++;
        }
        
        Arrays.sort(arr);
        int index = arr.length - 1;
        while(true){
            k = k - arr[index];
            answer++;
            index--;
            if(k <= 0){
                break;
            }
        }
        return answer;
    }
}