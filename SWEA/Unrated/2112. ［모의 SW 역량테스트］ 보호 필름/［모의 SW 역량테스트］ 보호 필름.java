import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

class Solution{
    static int D, W, K;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int tc=1;tc<=T;tc++){
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); //필름의 두께(세로)
            W = Integer.parseInt(st.nextToken()); //가로크기 (가로)
            K = Integer.parseInt(st.nextToken()); //합격 기준 K 셀들은 A 또는 B 특성 가지고 있음
            map = new int[D][W];
            for(int i = 0 ; i < D; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < W ; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int left = 0;
            int right = Integer.MAX_VALUE;
            int minInjection = right;


            while(left <= right){
                int mid = (left + right) / 2; //회수
                // , 조건에 합당하면 왼쪽 영역 탐색 , new int[D] 는 세로만 검사.
                if(canPass(mid, 0, 0, new int[D])){
                    right = mid - 1;
                    minInjection = mid; //약품 투여 횟수
                }else{ //약품 투여 회수가 mid보다 높으면 오른쪽 영역 탐색
                        left = mid + 1;
                }
            }

            System.out.println("#" + tc + " " + minInjection);
        }
    }

    public static boolean canPass(int mid, int count, int index, int[] apl){
        if( count > mid ) //백트래킹 , 약품 투여 회수가 기준치보다 높아지면 반환.
            return false;

        if(index == D){ //마지막 index까지 왔을 때
            return canValid(apl);
        }

        apl[index] = -1;
        if(canPass(mid , count , index+1, apl)){
            return true;
        }

        apl[index] = 0;
        if(canPass(mid, count+1, index+1, apl)){
            return true;
        }

        apl[index] = 1;
        if(canPass(mid, count+1, index+1, apl)){
            return true;
        }

        return false;

    }

    public static boolean canValid(int[] apl){
        for(int i = 0 ; i < W; i++){ //가로로
            int maxcount = 0; //최대 횟수
            int currentcount = 0; //현재 횟수
            int prev = - 1; //뒤로

            for(int j = 0 ; j < D; j++){ //세로로 k개 연속
                int cell = apl[j] == -1 ? map[j][i] : apl[j];

                if(cell==prev){
                    currentcount++;
                } else {
                    currentcount = 1;
                }
                prev = cell;
                maxcount = Math.max(maxcount, currentcount);
            }
            if(maxcount < K){ //최소 횟수도 못채우면 거짓.
                return false;
            }
        }
        return true;

    }
}