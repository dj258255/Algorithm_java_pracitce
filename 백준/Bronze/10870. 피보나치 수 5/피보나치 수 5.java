import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] number = new int[N+1];
		
		if(N >= 0) {
			number[0] = 0;
		}
		
		if(N >= 1) {
			number[1] = 1;
		}
		
		if(N > 1) {
			for(int i = 2; i <= N; i++) {
				number[i] = number[i-1] + number[i-2];
			}	
		}
		
		
		System.out.println(number[N]);
	}
}