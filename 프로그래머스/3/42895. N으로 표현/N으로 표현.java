import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

class Solution {
    public int solution(int N, int number) {
        if (N==number) return 1;
        Map<Integer, HashSet<Integer> > dp = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            dp.put(i,new HashSet<>());
        }
        
        int nNumber = 0;
        for(int i = 1; i<=8; i++){
            nNumber = nNumber*10+N;
            dp.get(i).add(nNumber);
        }
        
        //1 2. 3    4       5   6.   7           8
        //5 55 555 5555 55555 555555 5555555    55555555
        for(int i = 2; i <= 8; i++){
            for(int j = 1; j< i; j++){
                for(int a : dp.get(i-j)){
                    for(int b : dp.get(j)){
                        dp.get(i).add(a+b);
                        dp.get(i).add(a*b);
                        dp.get(i).add(a-b);
                        if(b != 0 && a !=0 ) dp.get(i).add(a/b);
                    }
                }
            }
            if(dp.get(i).contains(number)) return i;
        }
        
        
        

        
        
        return -1;
    }
}
