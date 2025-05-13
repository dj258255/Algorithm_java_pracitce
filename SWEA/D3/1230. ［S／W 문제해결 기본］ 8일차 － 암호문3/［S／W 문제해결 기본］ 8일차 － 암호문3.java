
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution{
	
	static int N;
	static int M;
	
	static int x,y;
	static ArrayList<Integer> list;
	public static void input() throws IOException{
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M; i++) {
			String temp = st.nextToken(); //문자
			
			if(temp.equals("I")) { //삽입 , 앞에서부터 x번째 암호문 바로 다음에 y개의 암호문을 삽입. s는 덧붙일 암호문들 
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				for(int j = 0;  j < y; j++) {
					list.add(x+j, Integer.parseInt(st.nextToken()));
				}
			} else if(temp.equals("D")) { //삭제, 앞에서부터 x번째 암호문 바로 다음부터 y개의 암호문을 삭제한
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				for(int j = 0 ; j < y; j++) {
					list.remove(x);
				}
			} else if(temp.equals("A")) { //추가 , 암호문 뭉치 맨 뒤에 y개의 암호문을 덧붙인다. s는 덧붙일 암호문들
				y = Integer.parseInt(st.nextToken());
				for(int j = 0 ; j < y ; j++) {
					list.add(Integer.parseInt(st.nextToken()));
				}
			}
		}
	}
	
	public static void solve() throws IOException {
		
		for(int i = 0; i < 10; i++) {
			bw.write(list.get(i) + " ");
		}
		
	}
	
	static BufferedReader br;
	static StringTokenizer st;
	static BufferedWriter bw;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int t=1; t<=10; t++) {
			
			input();
			
			bw.write("#" + t + " ");
			solve();
			bw.newLine();
			

		}
		
		bw.flush();
		br.close();
	}
}