import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] stones, int k) {
        int left = 0;
        int right = 200_000_001;
        
        //몇명이 건널 수 있는가
        //특정 인원이 건넜을 때 연속으로 0이 되는 구간의 길이가 k 이상인지를 판별 조건으로 쓰면 돼.
        while(left < right){
            int mid = (left+right)/2;
            int count = 0;
            boolean possible = false;
            
            for(int i = 0 ; i < stones.length; i++){
                //현재가 건널 수 없으면
                if(stones[i] - mid <= 0){
                    count++; //건널수 없는 행 카운트
                } else{ //건널 수 있으면
                    count = 0;
                }

                if(count >= k){
                    possible = true;
                    break;
                }
            }


            
            if(possible){ //못건넘 
                right = mid;
            }else{ //건널 수 있음
                left = mid+1;
            }
        }
        return left;
    }
}