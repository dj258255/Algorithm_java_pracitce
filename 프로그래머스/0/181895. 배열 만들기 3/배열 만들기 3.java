class Solution {
    public int[] solution(int[] arr, int[][] intervals) {
        
        int cnt = 0;
        
        for (int i = 0; i < intervals.length; i++) {
            cnt += (intervals[i][1] - intervals[i][0] + 1);
        }
        
        int[] answer = new int[cnt];        
        
        int idx = 0;
        
        for(int i = 0 ; i < intervals.length;i++){
            for(int j = intervals[i][0]; j <= intervals[i][1]; j++){
                answer[idx++] = arr[j];
            }
        }
        
        
        return answer;
    }
}