import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int[][] foods;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//그 음식의 신맛은 사용한 재료의 신맛의 곱이고 쓴맛은 합
		//신맛과 쓴맛의 차이를 적게 하려고 함
		//재료는 적어도 하나 사용해야함

		N = Integer.parseInt(st.nextToken()); //재료 N개
		foods = new int[N][2];
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			foods[i][0] = Integer.parseInt(st.nextToken()); //신맛
			foods[i][1] = Integer.parseInt(st.nextToken()); //쓴맛
		}
		
		sub(0, 1, 0,false);
		
		System.out.println(answer);
	}
	
	
	
	//a는 신맛 , b는 쓴맛으로 결정
	public static void sub(int index,int a,int b,boolean used) {
		if(index == N) {
			if(used) {
				int abs = Math.abs(a-b);
				answer = Math.min(answer, abs);
			}
			return;
		}
		
		
		//스킵
		sub(index+1, a,b,used);
		
		//스킵 안하고 곱 덥
		sub(index+1, a * foods[index][0] , b + foods[index][1],true);
		
	}
}