

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());
		
		int[] number = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(number);
		
		//중간이 이제 컷팅기의 기준
		// mid가 24라면 24내에몇개의 나무를 얻을 수있는
		int left = 0;
		int right = number[number.length-1];
		while(left <= right) {
			int mid = (left+right)/2;
			
			long sum = 0 ;
			
			for(int i = 0; i < N; i++) {
				if(number[i] > mid) {
					sum += number[i] - mid;
				}
			}

			if( sum >= M) {
				left = mid +1 ;
			} else {
				right = mid -1;
			}

		}
		
		System.out.println(right);
	}
}