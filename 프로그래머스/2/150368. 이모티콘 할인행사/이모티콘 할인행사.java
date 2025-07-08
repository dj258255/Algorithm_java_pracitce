class Solution {
    static int[] allDiscount = {10,20,30,40};
    static int maxJoin =0;
    static int maxPrice =0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        dfs(users,emoticons,new int[emoticons.length], 0);
        return new int[]{maxJoin,maxPrice};
    }
    
    public static void dfs(int[][] users, int[] emoticons, int[] discount, int depth){
        if(depth == emoticons.length){
            int joinCount = 0;
            int sumPrice = 0;
            
            for(int[] user : users){
                int limitPercent = user[0]; //이 퍼센트 넘기면 삼
                int limitPrice= user[1]; //이 가격 넘기면 플러스 삼
                int sum = 0;
                
                for(int i = 0 ; i < emoticons.length; i++){
                    if(discount[i] >= limitPercent){
                        double curPrice = emoticons[i] * ((100-discount[i])/100.0);
                        sum += curPrice;
                    }
                }
                
                
                if(sum >= limitPrice){
                    joinCount++;
                } else{
                    sumPrice += sum;
                }
            }
            
            if(joinCount > maxJoin){
                maxJoin = joinCount;
                maxPrice = sumPrice;
            } else if (joinCount == maxJoin) {
                maxPrice = Math.max(maxPrice, sumPrice);
            }
            return;
        }
        
        
        for(int cur : allDiscount){
            discount[depth] = cur;
            dfs(users,emoticons,discount,depth+1);
        }
        
        
    }
}