import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int companyX;
    static int companyY;
    static int homeX;
    static int homeY;
    static int[][] numbers;
    static int N;
    static int answer;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //고객의 수
            st = new StringTokenizer(br.readLine());
            numbers = new int[N][2];
            companyX = Integer.parseInt(st.nextToken()); //회사좌표 , 회사에서 출발
            companyY = Integer.parseInt(st.nextToken());
            homeX = Integer.parseInt(st.nextToken()); //집좌표
            homeY = Integer.parseInt(st.nextToken());
            for(int i = 0; i < N; i++) {
                numbers[i][0] = Integer.parseInt(st.nextToken()); //x좌표
                numbers[i][1] = Integer.parseInt(st.nextToken()); //y좌표
            }

            visited = new boolean[N];
            answer = Integer.MAX_VALUE;
            for(int i = 0 ; i < N; i++){
                visited[i] = false;
            }



            find(0,0,companyX,companyY);
            System.out.println("#" + tc + " " + answer);

        }
    }

    public static void find(int index,int distance ,int currentX, int currentY){
        if(distance >= answer) return;

        if(index == N){ //모든 손님 방문 완료
            //집에 돌아가는 경로
            distance += Math.abs(currentX - homeX) + Math.abs(currentY - homeY);
            answer = Math.min(answer,distance);
            return;
        }

        for(int i = 0; i < N ; i++){
            if(!visited[i]) { //방문 안했을 때
                visited[i] = true;
                int addedDistance = Math.abs(currentX - numbers[i][0]) + Math.abs(currentY - numbers[i][1]);
                find(index+1, distance+addedDistance, numbers[i][0] , numbers[i][1]);
                visited[i] = false;
            }
        }
    }
}