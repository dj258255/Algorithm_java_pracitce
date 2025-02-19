import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); //물품의 수
        int K = Integer.parseInt(st.nextToken()); //버틸 수 있는 무게
        
        int[] W = new int[N];  //물픔들의 무게
        int[] V = new int[N]; //물품들의 가치
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }
        
        //dp[j]는 무게가 j일 때, 선택할 수 있는 물건들로 얻을 수 있는 최대 가치를 저장해요.
        int[] dp = new int[K + 1];
        

        //물건 하나씩 확인
        for(int i = 0; i < N; i++) {
            //뒤에서부터 탐색(중복 사용 방지)
            for(int j = K; j >= W[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
            }
        }
        
        System.out.println(dp[K]);
    }
}
