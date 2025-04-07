import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); //앱의 개수
        int M = Integer.parseInt(st.nextToken()); //확보해야 하는 메모리
        
        int[] A = new int[N]; //각 앱이 사용하는 메모리
        int[] C = new int[N]; //각 앱을 비활성화할 때의 비용
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        int cost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N; i++) {
        	C[i] = Integer.parseInt(st.nextToken());
        	cost += C[i];
        }
        
        
        
        int[] dp = new int[cost+1];
        
        for(int i = 0 ; i < N; i++) {
        	for(int j = cost; j >= C[i]; j--) {
        		dp[j] = Math.max(dp[j], dp[j - C[i]] + A[i] );
        	}
        }
        
        for(int i = 0 ; i <= cost; i++) {
        	if(dp[i]>=M) {
        		System.out.println(i);
        		break;
        	}
        }
        
    }
}
