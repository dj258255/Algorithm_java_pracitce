class Solution {
    public int solution(int n) {
        int answer = 0;
        
        
        int sum =1; //합
        int left = 1;
        int right = 1;
        while(right <= n){
            if(sum == n){
                System.out.print(left);
                System.out.println(right);
                answer++; //더한값이 n이랑 같으면 answer 더하기
                sum -= left;
                left++;
            } else if(sum > n){
                sum -= left; //더크면 왼쪽값 제거
                left++;
            } else if(sum < n){
                right++;
                sum += right; //더 작으면 오른쪽 값 추가
            }
            
        }
        
        return answer;
    }
}