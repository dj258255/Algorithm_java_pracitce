import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		//동전의 가치
		int[] numbers = new int[N];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			numbers[i] = Integer.parseInt(st.nextToken()); 
		}
		
		int answer =0 ;
		for(int i = N-1; i >= 0; i--) {
			answer += K / numbers[i];
			K %= numbers[i];
		}
		
		System.out.println(answer);
	}
}