import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] elements) {
        
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        for(int i = 0 ; i < n; i++){ //현재부터 시작 부분 수열 더하자
            int sum = 0;
            for(int j = 0 ; j < n; j++){ //n까지의 부분수열을 더해보자 
                int element = elements[(i+j)%n];
                sum += element;
                set.add(sum);
            }
        }
        
        
        return set.size();
    }
}