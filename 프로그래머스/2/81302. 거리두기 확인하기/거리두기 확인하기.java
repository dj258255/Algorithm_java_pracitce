import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
    
    static int[][] map;
    static int[] answer;
    public int[] solution(String[][] places) {
        //응시자 P
        //빈 테이블 0
        //파티션 X
        answer = new int[5];
        
        Arrays.fill(answer,1); //전부다 잘 지키는걸로 가자고
        

        for(int t = 0; t < 5 ; t++){
        //input --START
        map = new int[5][5];
        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 5; j++){
                map[i][j] = places[t][i].charAt(j);
            }
        }
        //input --END
            
        bfs_form(t); //몇번 째 테스트 케이스인지

            
            
        }

        return answer;
    }
    
    public static void bfs_form(int t){
        for(int i = 0 ; i < 5; i++){
            for(int j = 0 ; j < 5; j++){
                if(map[i][j] == 'P'){
                    answer[t] = bfs(i,j);
                    if(answer[t] == 0) return;
                }
            }
        }
    }
    

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static int bfs(int startR, int startC){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startR,startC});
        boolean[][] visited = new boolean[5][5]; //맵은 항상 5x5임
        visited[startR][startC] = true;
        
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            for(int d = 0 ; d < 4; d++){
                int nr = node[0] + dr[d]; //node[0]은 cr 
                int nc = node[1] + dc[d]; //node[1] 은 cc
                if(nr >= 5 || nc >= 5 || nr < 0 || nc < 0) continue; //맵 밖
                if(map[nr][nc] == 'X') continue; //만약 파티션이 있으면 건너뛰기
                //|r1 - r2| + |c1 - c2|,만약 시작 지점에서 맨헤튼 거리.  이상으로 벗어나면 컨티뉴
                if(Math.abs(startR-nr) + Math.abs(startC - nc) > 2 ) continue;
                if(visited[nr][nc]) continue; //방문했으면 컨티뉴
                
                
                //위 조건들 뚫었는데 참가자가 있으면 바로 리턴
                if(map[nr][nc] == 'P'){
                    return 0;
                }
                //위 조건들 뚫었는데 참가자가 없으면 방문처리
                visited[nr][nc] = true; //방문처리
                queue.add(new int[]{nr,nc}); //다음 방문 추가
                


            }
        }
        
        //어기까지 왔으면 위 조건들 잘.지킨거니 1. 반환
        return 1;
    }
    
}