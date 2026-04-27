import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        
        //한 번에 K 칸을 앞으로 점프하거나 현재까지 온 거리 * 2 에 해당하는 위치로 순간이동
        //순간이동은 건전지 사용량 줄지 않음. K간 점프하면 K만큼의 건전지 사용
        //점프로 이동하는건 최소로 
        while(n > 0){
            if(n%2 == 0){ //짝수면 순간이동으로 온 것 
                n = n/2;
            } else if(n%2 == 1){ //홀수면 점프
                n -= 1;
                ans++;
            }

        }
        
        return ans;
    }
}