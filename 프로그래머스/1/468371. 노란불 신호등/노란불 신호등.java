class Solution {
    public int solution(int[][] signals) {
        for(int i = 0; i < 10000000; i++){
            boolean allYellow = true;
            
            for(int j = 0; j < signals.length; j++) {
                int g = signals[j][0];
                int y = signals[j][1];
                int r = signals[j][2];
                int period = g + y + r;
                
                int position = (i - 1) % period;
                
                if(!(position >= g && position < g + y)) {
                    allYellow = false;
                    break;
                }
            }
            
            if(allYellow) return i;
        }
        
        return -1;
    }
}