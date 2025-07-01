class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliverSum = 0;
        int pickupSum = 0;
        for(int i = n-1; i >= 0; i--){
            deliverSum += deliveries[i];
            pickupSum += pickups[i];
            while(deliverSum > 0 || pickupSum > 0){
                deliverSum -= cap;
                pickupSum -= cap;
                answer += (i+1) * 2;
            }
            
            
        }
        return answer;
    }
}