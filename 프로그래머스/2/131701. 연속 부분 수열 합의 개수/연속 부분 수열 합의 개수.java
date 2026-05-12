import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        
        int n = elements.length;
        for(int i = 0; i < n; i++){ //한번씩 돌아가기
            int sum = 0;
            for(int j = 0 ; j < n; j++){ //크기
                int element = elements[(i+j)%n];
                sum += element;
                set.add(sum);
            }
        }
        
        return set.size();
    }
}