class Solution {
    static int[] dr = {1,0,-1}; //하 우 좌상
    static int[] dc = {0,1,-1};
    public int[] solution(int n) {
        if(n == 1) return new int[]{1};
        
        int answer[];
        
        int r = 0, c= 0, d = 0, num = 1;
        
        int[][] snail = new int[n][n];
        
        while(snail[r][c] == 0){
            
            snail[r][c] = num++;
            
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if(nr >= n || nc >= n || snail[nr][nc] != 0 || nr < 0 || nc <0){
                d = (d+1) %3;
            }
            
            r = r + dr[d];
            c = c + dc[d];
        }
        
        answer = new int[num-1];
        
        int temp = 0;
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                if(snail[i][j] != 0){
                    answer[temp++] = snail[i][j];
                }
            }
        }
        
        
        return answer;
    }
}