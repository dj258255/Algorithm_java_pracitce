import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        List<Integer> blist = new ArrayList<>();

        
        for(int i = 0 ; i < B.length; i++){
            blist.add(B[i]);
        }
        blist.sort((a,b)->a-b);
        
        for(int i = 0 ; i < A.length; i++){
            int goal = A[i]; //이넘보다 커야함
            
            int left = 0;
            int right = blist.size();
            while(left < right){
                int mid = (left+right)/2; //B의 중간 사이즈 
                
                if(blist.get(mid) <= goal){ // < 에서 <= 로
                    left = mid+1;
                } else{
                    right = mid;
                }
            }
            
            if(left < blist.size() && blist.get(left) > goal){ //목표치보다 높았던게 맞았다면 
                answer++;
                blist.remove(left); //삭제
            } else{
                blist.remove(0); //그냥 삭제
            }
        }
        
        return answer;
    }
}