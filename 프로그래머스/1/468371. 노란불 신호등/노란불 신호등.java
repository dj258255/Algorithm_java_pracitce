class Solution {
    public int solution(int[][] signals) {
        
        for(int i = 0 ; i < 10000000; i++){
            boolean possible = true;
            for(int j = 0 ; j < signals.length; j++){
                int g = signals[j][0];
                int y = signals[j][1];
                int r = signals[j][2];
                int period = g+y+r;
                
                int curY = (i-1) % period;
                
                if(!(curY >= g && curY < g + y)) {
                    possible = false;
                    break;
                }
                
            }
            
            
            if(possible) return i;
        }
        
        return -1;
    }
}