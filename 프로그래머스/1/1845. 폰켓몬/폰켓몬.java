import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        HashSet<Integer> poketmons = new HashSet<>();
        for(int i = 0 ; i < nums.length; i++){
            poketmons.add(nums[i]);
        }
        
        if(poketmons.size() > ((nums.length)/2)){
            answer = nums.length/2;
        } else{
            answer = poketmons.size();
        }
        
        
        
        
        
        return answer;
    }
}