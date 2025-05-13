import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int N,M,c1,c2;
	static int[] cow;
	static int[] horse;
	public static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //소의 수
		M = Integer.parseInt(st.nextToken()); //말의 수

		//모든 소는 x=c1, y= 0 직선상에 존재
		//말은 x=c2,y=0 직선상에 존재
		
		// |x2-x1| + |y2-y1| + |z2-z1|

		st = new StringTokenizer(br.readLine()); 
		c1 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		
		//소들의 위치 N개의 정수
		//말들의 위치 M개의 정수 
		cow = new int[N];
		horse = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			cow[i] =  Integer.parseInt(st.nextToken());
		}
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			horse[i] = Integer.parseInt(st.nextToken());
//			horse[i] += Math.abs(c2-c1); 
		}
		
		Arrays.sort(horse);
	}
	
	static int left,right;
	static int min;
	static int count;
	public static void solve() throws IOException {
	    int len = Math.abs(c2 - c1);
	    
	    min   = Integer.MAX_VALUE;
	    count = 0;

	    for (int i = 0; i < N; i++) {
	        //small-bound
	        left  = 0;
	        right = M - 1;
	        while (left < right) {
	            int mid = (left + right) / 2;
	            if (horse[mid] < cow[i]) {
	            	left  = mid + 1;
	            } else {
	            	right = mid;
	            }
	        }

           int small = (horse[left] <= cow[i] ? left : left - 1);
	        
	        //big-bound
	        left  = 0;
	        right = M - 1;
	        while (left < right) {
	            int mid = (left + right) / 2;
	            if (horse[mid] <= cow[i]) {
	            	left  = mid + 1;
	            } else {
	            	right = mid;
	            }
	        }

	        int big = (horse[left] > cow[i] ? left : -1);
	        

		        if (small >= 0) {
		            int dist = len + Math.abs(horse[small] - cow[i]);
		            if (dist < min) {
		                min   = dist;
		                count = 1;
		            } else if (dist == min) {
		                count++;
		            }
		        }

		        if (big >= 0) {
		            int dist = len + Math.abs(horse[big] - cow[i]);
		            if (dist < min) {
		                min   = dist;
		                count = 1;
		            } else if (dist == min) {
		                count++;
		            }
		        }
//		        else if(small == big) {
//			        if (small >= 0) {
//			            int dist = len + Math.abs(horse[small] - cow[i]);
//			            if (dist < min) {
//			                min   = dist;
//			                count = 1;
//			            } else if (dist == min) {
//			                count++;
//			            }
//			        }
//		        }

	    } //소 다 돌아버림

	    
	}


		

	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int Tc = Integer.parseInt(br.readLine());
		
		for(int t= 1; t <= Tc; t++) {
			input();
			

			solve();
			
			bw.write("#" + t + " " + min + " " + count);
			bw.newLine();
		}
		bw.flush();
		br.close();
	}
}