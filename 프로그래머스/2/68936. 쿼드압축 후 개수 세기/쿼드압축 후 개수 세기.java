class Solution {
    int[] answer = new int[2];
    
    public int[] solution(int[][] arr) {
        dfs(arr, 0, 0, arr.length);
        return answer;
    }

    //압축 가능?
    public boolean possible(int[][] arr, int r, int c, int len) {
        int val = arr[r][c];

        //완탐
        for(int i = r; i < r + len; i++) { //r부터 r+len
            for(int j = c; j< c + len; j++) { // c 부터 c + len
                if(val != arr[i][j]) return false;
            }
        }
        return true;
    }

    public void dfs(int[][] arr, int r, int c, int len) {

        //압축가능
        if(possible(arr, r, c, len)) {
            if(arr[r][c] == 0) answer[0]++;//0이니 0 증가
            else answer[1]++;// 1증가
            return; //이제 분할 ㄴㄴ
        //압축불가
        } else {
            dfs(arr, r, c, len/2);//왼쪽 위
            dfs(arr, r, c+len/2, len/2);//오른쪽 위
            dfs(arr, r+len/2, c, len/2); //왼쪽 아래
            dfs(arr, r+len/2, c+len/2, len/2);//오른쪽 아래
        }
    }
}