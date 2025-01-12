import java.util.ArrayDeque;
import java.util.Scanner;

public class Solution{
    static int N;
    static char[][] matrix;
    static boolean[][] visited;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        for(int t = 1; t <= T; t++){
            N = scan.nextInt();
            matrix = new char[N][N];
            visited = new boolean[N][N];

            for(int i = 0; i<N; i++){
                String temp = scan.next();
                for(int j = 0; j<N; j++){
                    matrix[i][j] = temp.charAt(j);
                }
            }

            int result = find();

            System.out.println("#" + t + " " + result );

        }
    }

    //최소클릭 찾자
    public static int find(){
        int[][] numMatrix = new int[N][N];

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(matrix[i][j] == '*'){
                    numMatrix[i][j] = -1; //지뢰 표시
                } else {
                    numMatrix[i][j] = countAdjacentMines(i,j); //인접한 지뢰 세기
                }
            }
        }

        int clicks = 0;

        //0인 영역 먼저 열기
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j<N; j++){
                if(numMatrix[i][j] == 0 && !visited[i][j]){
                    openRegion(numMatrix,i,j);
                    clicks++;
                }
            }
        }

        //나머지 칸 열기
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if( numMatrix[i][j] > 0 && !visited[i][j]){
                    clicks++;
                }
            }
        }

        return clicks;
    }

    public static int countAdjacentMines(int x, int y){
        int count = 0;
        for(int d = 0 ; d < 8; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if ( isInBounds(nx,ny) && matrix[nx][ny] == '*') {
                count++;
            }
        }
        return count;
    }


    public static void openRegion(int[][] numMatrix, int x, int y){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for(int d = 0; d < 8; d++){
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if( isInBounds(nx,ny) && !visited[nx][ny] && numMatrix[nx][ny] >= 0){
                    visited[nx][ny] = true;

                    //0인 경우에만 큐에 추가
                    if(numMatrix[nx][ny] == 0){
                        queue.add(new int[]{nx,ny});
                    }
                }
            }
        }
    }

    public static boolean isInBounds(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}