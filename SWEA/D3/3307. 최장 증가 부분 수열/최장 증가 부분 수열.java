import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			
			int[] arr = new int[N];
			
			for(int i = 0 ; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
	        int[] C = new int[N]; // 각 길이을 LIS로 만족하는 가장 끝에 오는 최소값
	        
	        int size = 0;// LIS 길이
	        for(int i = 0 ; i < N; i++) {
	        	//i 원소가 최소값으로 끝에 설 수 있는 위치 찾기
	        	int pos = Arrays.binarySearch(C, 0,size,arr[i]); //못찾으면 -insertion point - 1
	        	if(pos>=0) return; //찾는 값이 있따는 얘기는 값의 중복이 있다는 의미! : 문제에 따라 다르게 처리
	        	
	        	int temp =Math.abs(pos) -1;
	        	C[temp] = arr[i];
	        	
	        	if(temp == size) ++size; // 맨뒤에 추가된 상황이므로 사이즈 증가
	        }
	        
			
			System.out.println("#" + tc + " " + size);
		}
	}
}