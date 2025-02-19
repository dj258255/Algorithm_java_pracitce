import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main{
	static int N,M;
	static int[] numbers;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M]; //선택된 수 저장
		isSelected = new boolean[N+1]; // 1부터 사용, 선택 여부 저장
		
		permutation(0);
	}
	
	static void permutation(int cnt) {
		
		if(cnt == M) {
			for(int i = 0 ; i < numbers.length; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();

			return;
		}
		
		for( int i = 1; i<= N; i++) { //유도 파트
			if(isSelected[i]) continue;
			numbers[cnt] = i; //선택한 수 저장 
			isSelected[i]= true; //선택한 수 플래그 true
			permutation(cnt+1); //다음 수 선택하러 고
			isSelected[i]= false; //선택 변경해야하니 기존 플래그 원복 
		}
	}
}