class Solution {
    public int solution(int[] stones, int k) {
        int left = 0;
        int right = 200_000_001;
        
        while(left < right){
            int mid = (left+right)/2;
            boolean possible = false;
            int count =0;
            for(int i = 0; i < stones.length; i++){
                if(stones[i]-mid <= 0){ //만약 0이하면 못건너는거니 카운트
                    count++;
                } else{ //건널 수 있으니 초기화
                    count=0;
                }
                
                if(count >= k){ //만약 뒤딤기 해도 못건너면
                    possible = true; //못건넘
                    break;
                }
            }
            
            if(possible){ //못건너면
                right = mid; //가용인원 줄이기
            } else{
                left = mid+1; //가용인원 늘리기
            }
        }
        
        
        return left;
    }
}